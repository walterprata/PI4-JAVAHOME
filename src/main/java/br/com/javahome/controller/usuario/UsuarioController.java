package br.com.javahome.controller.usuario;

import br.com.javahome.component.Utilidades;
import br.com.javahome.model.Endereco;
import br.com.javahome.model.usuario.Usuario;
import br.com.javahome.model.usuario.UsuarioLogado;
import br.com.javahome.repository.EnderecoRepository;
import br.com.javahome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.javahome.Constantes.*;

@RestController
@RequestMapping("/javaHome")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private Utilidades utilidades;
    @Autowired
    private UsuarioLogado usuarioLogado;

    private static final String SESSION_ATRIBUTE_EMAIL = "email";
    private static final String SESSION_ATRIBUTE_CARGO = "cargo";
    private static final String SESSION_ATRIBUTE_USER_NAME = "nome";
    private static final String SESSION_ATRIBUTE_USER_ID = "id";
    private static final String CARGO_ESTOQUISTA = "estoquista";

    @GetMapping(value = {"/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping(value = {"/login/cadastrar"})
    public ModelAndView cadastra() {
        ModelAndView modelAndView = verificaCargo();
        if (modelAndView != null) {
            return modelAndView;
        }
        return new ModelAndView(CADASTRA_USUARIO_JSP);
    }

    @GetMapping(value = {"/usuario/editar/cliente"})
    public ModelAndView editarInfoDoCliente() {
        ModelAndView modelAndView = new ModelAndView(CADASTRA_USUARIO_JSP);
        String attribute = session.getAttribute(SESSION_ATRIBUTE_USER_ID).toString();
        if (attribute != null) {
            Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(Integer.parseInt(attribute));
            if (usuarioEncontrado.isPresent()) {
                Usuario usuario = usuarioEncontrado.get();
                modelAndView.addObject("cliente", usuario);
            }
        }
        return modelAndView;
    }

    private ModelAndView verificaCargo() {
        if (session.getAttribute(SESSION_ATRIBUTE_CARGO) != null && session.getAttribute(SESSION_ATRIBUTE_CARGO).equals(CARGO_ESTOQUISTA)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            return modelAndView;
        }
        return null;
    }

    @PostMapping("/auth/login")
    public ModelAndView authUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        //VALIDA USUARIO NO BANCO DE DADOS
        Usuario usuario = usuarioRepository.validaUsuario(email);
        ModelAndView modelAndView = login();
        if (usuario != null && utilidades.validaSenha(senha, usuario.getSenha())) {
            if (usuario.getStatus()) {
                usuarioLogado.setUsuario(usuario);
                session.setAttribute(SESSION_ATRIBUTE_EMAIL, usuario.getEmail());
                session.setAttribute(SESSION_ATRIBUTE_CARGO, usuario.getCargo());
                session.setAttribute(SESSION_ATRIBUTE_USER_NAME, usuario.getNome());
                session.setAttribute(SESSION_ATRIBUTE_USER_ID, usuario.getId());
                modelAndView = new ModelAndView(REDIRECT_JAVA_HOME);
            } else {
                modelAndView.addObject(MESSAGE, USUÁRIO_DESATIVADO_POR_FAVOR_CONTATE_O_ADMINISTRADOR);
            }
        } else {
            modelAndView.addObject(MESSAGE, USUÁRIO_NÃO_ENCONTRADO);
        }
        return modelAndView;
    }
    // CANAL MOBILE API
    @PostMapping("/auth/mobile/login")
    public Usuario  authUsuarioMobile(@RequestBody  Usuario usuario, HttpServletResponse response) {
        //VALIDA USUARIO NO BANCO DE DADOS
        Usuario usuarioEncontrado = usuarioRepository.validaUsuario(usuario.getEmail());
        ModelAndView modelAndView = login();
        if (usuario != null && utilidades.validaSenha(usuario.getSenha(), usuarioEncontrado.getSenha())) {
            if (usuario.getStatus()) {
                usuarioLogado.setUsuario(usuario);
                session.setAttribute(SESSION_ATRIBUTE_EMAIL, usuarioEncontrado.getEmail());
                session.setAttribute(SESSION_ATRIBUTE_CARGO, usuarioEncontrado.getCargo());
                session.setAttribute(SESSION_ATRIBUTE_USER_NAME, usuarioEncontrado.getNome());
                session.setAttribute(SESSION_ATRIBUTE_USER_ID, usuarioEncontrado.getId());
                return usuarioEncontrado;
            } else {
               response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return null;
    }

    //LISTAR TODOS ATIVOS E INATIVOS
    @GetMapping("/auth/listar-todos-usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    //LISTAR TODOS ATIVOS
    @GetMapping("/auth/listar-usuarios-ativos")
    public List<Usuario> listarUsuariosAtivos() {
        return usuarioRepository.ListarUsuariosAtivos();
    }

    //BUSCA UM USUÁRIO ESPECIFICO PELO ID
    @GetMapping("/auth/usuario/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    //CADASTRA UM USUÁRIO
    @PostMapping("/auth/cadastrar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView cadastrarUsuario(@Valid @ModelAttribute Usuario usuario, @RequestParam(value = "enderecos[]", required = false) String[] enderecos) {
        ModelAndView modelAndView = cadastra();
        try {
            inserirEnderecos(usuario, enderecos);
            salvaUsuario(usuario, modelAndView);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
            modelAndView.addObject(MESSAGE_ERROR, ERRO_AO_SALVAR + CPF_CADASTRADO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelAndView.addObject(MESSAGE_ERROR, ERRO_AO_SALVAR + e.getMessage());
        }
        return modelAndView;
    }

    private void formataEnderecos(String[] enderecos, ArrayList<Endereco> enderecosFormatados) {
        int quantidadeDeIntes = enderecos.length - 1;
        Endereco endereco;
        for (int i = 0; i <= quantidadeDeIntes; i++) {
            String[] split = enderecos[i].split(";");
            endereco = new Endereco();
            for (int index = 0; index <= split.length - 1; index++) {
                 criaEndereco(endereco,split, index);
            }
            enderecosFormatados.add(endereco);
        }
    }

    private void criaEndereco(Endereco endereco, String[] split, int index) {
        switch (index) {
            case 0:
                endereco.setCep(split[0]);
                break;
            case 1:
                endereco.setBairro(split[1]);
                break;
            case 2:
                endereco.setLocalidade(split[2]);
                break;
            case 3:
                endereco.setLogradouro(split[3]);
                break;
            case 4:
                endereco.setUf(split[4]);
                break;
            case 5:
                endereco.setComplemento(split[5]);
                break;
        }
    }

    private void salvaUsuario(Usuario usuario, ModelAndView modelAndView) {
        System.out.println("Usuário na seção: " + session.getAttribute(SESSION_ATRIBUTE_EMAIL));
        Object emailNaSecao = session.getAttribute(SESSION_ATRIBUTE_EMAIL);

        if (emailNaSecao == null || !emailNaSecao.equals(usuario.getEmail())) {
            usuario.setSenha(utilidades.encryptaSenha(usuario.getSenha()));
            usuarioRepository.save(usuario);
            modelAndView.addObject(MESSAGE_SUCCES, USUÁRIO_FOI_SALVO);
        } else {
            throw new RuntimeException(ESTE_USUÁRIO_ESTA_SENDO_USADO_NO_MOMENTO);
        }
    }

    //CADASTRA UM USUÁRIO
    @PostMapping("/auth/edita-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView editar(@Valid @ModelAttribute() Usuario usuario, @RequestParam(value = "enderecos[]", required = false) String[] enderecos) {
        ModelAndView modelAndView = editarInfoDoCliente();
        try {
            String emailNaSessao = session.getAttribute(SESSION_ATRIBUTE_EMAIL).toString().trim();
            System.out.println(emailNaSessao);
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());
            if (!session.getAttribute(SESSION_ATRIBUTE_EMAIL).equals(usuario.getEmail()) || usuario.getCargo().equals("Cliente")) {
                if (usuarioOptional.isPresent()) {
                    Usuario usuarioEncontrado = usuarioOptional.get();
                    if (!usuarioEncontrado.getEmail().equals(usuario.getEmail())) {
                        throw new RuntimeException(O_EMAIL_LOGIN_NÃO_PODE_SER_ALTERADO);
                    }else if (!emailNaSessao.equals(usuarioEncontrado.getEmail())){
                        modelAndView = cadastra();
                    }

                    usuario.setSenha(utilidades.encryptaSenha(usuario.getSenha()));
                    inserirEnderecos(usuario, enderecos);
                    usuarioRepository.save(usuario);
                    usuarioLogado.setUsuario(usuario);
                    modelAndView.addObject(MESSAGE_SUCCES, USUÁRIO_FOI_SALVO);
                } else {
                    throw new RuntimeException(O_USUÁRIO_NÃO_FOI_ENCONTRADO_NA_BASE_DE_DADOS);
                }
            } else {
                throw new RuntimeException(ESTE_USUÁRIO_ESTA_SENDO_USADO_NO_MOMENTO);
            }
        } catch (Exception e) {
            modelAndView.addObject(MESSAGE_ERROR, ERRO_AO_SALVAR + e.getMessage());
        }
        return modelAndView;
    }



    private void inserirEnderecos(@ModelAttribute @Valid Usuario usuario, @RequestParam("enderecos[]") String[] enderecos) {
        ArrayList<Endereco> enderecosFormatados = new ArrayList<>();
        if (enderecos != null && enderecos.length - 1 >= 0) {
            formataEnderecos(enderecos, enderecosFormatados);
            enderecosFormatados.forEach(e -> e = enderecoRepository.save(e));
            usuario.setEndereco(enderecosFormatados);
        }
    }

    //ATIVA OU DESATIVA UM USUÁRIO
    @PostMapping("/auth/deleta-usuario/{id}/{status}")
    public HttpStatus remover(@PathVariable Integer id, @PathVariable boolean status) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            System.out.println(session.getAttribute(SESSION_ATRIBUTE_USER_ID));
            if (!session.getAttribute(SESSION_ATRIBUTE_USER_ID).equals(usuario.getId())) {
                usuario.setStatus(status);
                usuarioRepository.save(usuario);
                return HttpStatus.OK;
            } else {
                return HttpStatus.BAD_REQUEST;
            }
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping("/adcionar-produto")
    public ModelAndView addProduto() {
        if (session.getAttribute(SESSION_ATRIBUTE_USER_ID) == null) {
            return login();
        } else {
            return new ModelAndView("redirect:/javaHome/");
        }
    }
}

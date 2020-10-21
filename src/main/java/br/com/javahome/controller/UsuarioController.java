package br.com.javahome.controller;

import br.com.javahome.model.Usuario;
import br.com.javahome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static br.com.javahome.Constantes.*;

@RestController
@RequestMapping("/javaHome")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private HomeController homeRotas;

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

    private ModelAndView verificaCargo() {
        if (session.getAttribute(SESSION_ATRIBUTE_CARGO).equals(CARGO_ESTOQUISTA)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            return modelAndView;
        }
        return null;
    }

    @PostMapping("/auth/login")
    public ModelAndView authUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        //VALIDA USUARIO NO BANCO DE DADOS
        Usuario usuario = usuarioRepository.validaUsuario(email, senha);
        ModelAndView modelAndView = login();
        if (usuario != null) {
            if (usuario.getStatus()) {
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
    public ModelAndView cadastrarUsuario(@Valid @ModelAttribute() Usuario usuario) {
        ModelAndView modelAndView = cadastra();
        try {
            System.out.println(session.getAttribute(SESSION_ATRIBUTE_EMAIL));
            if (!session.getAttribute(SESSION_ATRIBUTE_EMAIL).equals(usuario.getEmail())) {
                usuarioRepository.save(usuario);
                modelAndView.addObject(MESSAGE_SUCCES, USUÁRIO_FOI_SALVO);
            } else {
                throw new RuntimeException(ESTE_USUÁRIO_ESTA_SENDO_USADO_NO_MOMENTO);
            }
        } catch (Exception e) {
            modelAndView.addObject(MESSAGE_ERROR, ERRO_AO_SALVAR+ e.getMessage());
        }
        return modelAndView;
    }

    //CADASTRA UM USUÁRIO
    @PostMapping("/auth/edita-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView editar(@Valid @ModelAttribute() Usuario usuario) {
        ModelAndView modelAndView = cadastra();
        try {
            System.out.println(session.getAttribute(SESSION_ATRIBUTE_EMAIL));
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());
            if (!session.getAttribute(SESSION_ATRIBUTE_EMAIL).equals(usuario.getEmail())) {
                if (usuarioOptional.isPresent()){
                    Usuario usuarioEncontrado = usuarioOptional.get();
                    if (!usuarioEncontrado.getEmail().equals(usuario.getEmail())) {
                        throw new RuntimeException(O_EMAIL_LOGIN_NÃO_PODE_SER_ALTERADO);
                    }
                    usuarioRepository.save(usuario);
                    modelAndView.addObject(MESSAGE_SUCCES, USUÁRIO_FOI_SALVO);
                }else{
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
    public ModelAndView addProduto(){
        if (session.getAttribute(SESSION_ATRIBUTE_USER_ID) == null){
            return login();
        }else{
            return new ModelAndView("redirect:/javaHome/");
        }
    }
}

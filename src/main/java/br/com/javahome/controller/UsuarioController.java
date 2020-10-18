package br.com.javahome.controller;

import br.com.javahome.model.Usuario;
import br.com.javahome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/javaHome")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HttpSession session;

    private static final String SESSION_ATRIBUTE_EMAIL = "email";
    private static final String SESSION_ATRIBUTE_CARGO = "cargo";

    @GetMapping(value = {"/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping(value = {"/login/cadastrar"})
    public ModelAndView cadastra(){
        ModelAndView modelAndView = new ModelAndView("cadastraUsuario");
        return modelAndView;
    }

    @PostMapping("/auth/login")
    public ModelAndView authUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        //VALIDA USUARIO NO BANCO DE DADOS
    	Usuario usuario = usuarioRepository.validaUsuario(email, senha);
    	ModelAndView modelAndView;
        if (usuario != null) {
            session.setAttribute(SESSION_ATRIBUTE_EMAIL, usuario.getEmail());
        	session.setAttribute(SESSION_ATRIBUTE_CARGO, usuario.getCargo());
        	modelAndView = new ModelAndView("index");
        }else{
            modelAndView = new ModelAndView("login");
            modelAndView.addObject("message","Usuário não encontrado!");
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
        ModelAndView modelAndView = new ModelAndView("cadastraUsuario");
        try {
            if (session.getAttribute(SESSION_ATRIBUTE_EMAIL).equals(usuario.getEmail())){
                usuarioRepository.save(usuario);
                modelAndView.addObject("messageSucces", "Usuário foi Salvo!");
            }else{
                throw new RuntimeException("Este usuário esta sendo usado no momento");
            }
        }catch (Exception e){
            modelAndView.addObject("error", "Erro ao salvar: " + e.getMessage());
        }
        return modelAndView;
    }

    //ATIVA OU DESATIVA UM USUÁRIO
    @PutMapping("/auth/deleta-usuario/{id}/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id,@PathVariable boolean status) {
        Usuario p = usuarioRepository.findById(id).get();
        if (p != null) {
            p.setStatus(status);
            usuarioRepository.save(p);
        }
    }
}

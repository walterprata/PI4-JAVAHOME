package br.com.javahome.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.javahome.model.Usuario;
import br.com.javahome.repository.UsuarioRepository;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/javaHome")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public String authUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha , Model theModel, HttpSession session) {
        //VALIDA USUARIO NO BANCO DE DADOS
    	Usuario usuario = usuarioRepository.validaUsuario(email, senha);
        if (usuario != null) {
        	session.setAttribute("email", usuario.getEmail());
        	session.setAttribute("cargo", usuario.getCargo());
            return "Usuario Logado";
        }
        return "Usuario invalido";
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
    public ModelAndView cadastrarUsuario( @Valid @ModelAttribute Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("cadastraUsuario");
        try {
            usuarioRepository.save(usuario);
            modelAndView.addObject("messageSucces", "Usuário foi Salvo!");
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

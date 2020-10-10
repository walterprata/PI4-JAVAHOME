package br.com.javahome.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javahome.model.Usuario;
import br.com.javahome.repository.UsuarioRepository;

@RestController
@RequestMapping("/javahome")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public String listUsuarios(@RequestParam("email") String email, @RequestParam("senha") String senha, @RequestParam("cargo") String cargo , Model theModel, HttpSession session) {
        //VALIDA USUARIO NO BANCO DE DADOS
    	Usuario usuario = usuarioRepository.validaUsuario(email, senha);
        if (usuario != null) {
        	session.setAttribute("email", email);
        	session.setAttribute("cargo", cargo);
            return "Usuario Logado";
        }
        
        return "Usuario invalido";
    }

    //LISTAR TODOS ATIVOS E INATIVOS
    @GetMapping("/listar-todos-usuarios")
    public List<Usuario> pesquisarUsuario() {
        return usuarioRepository.findAll();
    }
    
    //LISTAR TODOS ATIVOS
    @GetMapping("/listar-usuarios-ativos")
    public List<Usuario> listarUsuariosAtivos() {
        return usuarioRepository.ListarUsuariosAtivos();
    }
    
    //BUSCA UM USUÁRIO ESPECIFICO PELO ID
    @GetMapping("usuario/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    //CADASTRA UM USUÁRIO
    @PostMapping("/cadastrar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario( @Valid @RequestBody Usuario usuario) {
    	usuarioRepository.save(usuario);
    }

    //DELETA UM USUÁRIO DE FORMA INSERINDO FALSE (DESATIVANDO) O STATUS
    @DeleteMapping("/deleta-usuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        Usuario p = usuarioRepository.findById(id).get();
        if (p != null) {
            p.setStatus(false);
            usuarioRepository.save(p);
        }
    }
}

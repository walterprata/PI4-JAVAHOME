package br.com.javahome.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    public String listUsuarios(@RequestParam("email") String email, @RequestParam("senha") String senha, Model theModel) {
        Usuario usuario = usuarioRepository.validaUsuario(email, senha);
        if (usuario != null) {
            return "Usuario Logado";
        }
        return "Usuario invalido";
    }


    @GetMapping("/listar-usuario")
    public List<Usuario> pesquisarUsuario() {
        return usuarioRepository.findAll();
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarProduto(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @PostMapping("/deletar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}

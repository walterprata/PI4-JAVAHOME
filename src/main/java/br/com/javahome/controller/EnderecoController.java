package br.com.javahome.controller;

import br.com.javahome.model.Endereco;
import br.com.javahome.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/javaHome")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @RequestMapping("/endereco/{id}")
    public Endereco pegarEnderecoDoUsuario(@PathVariable Integer id) {
        Optional<Endereco> enderecoResposta = enderecoRepository.findById(id);
        return enderecoResposta.orElseGet(Endereco::new);
    }

    @PostMapping("/endereco")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvaEndereco(@ModelAttribute Endereco endereco){
        enderecoRepository.save(endereco);
    }
}

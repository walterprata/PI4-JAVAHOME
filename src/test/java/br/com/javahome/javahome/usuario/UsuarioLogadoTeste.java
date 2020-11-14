package br.com.javahome.javahome.usuario;

import br.com.javahome.model.Endereco;
import br.com.javahome.model.usuario.Usuario;
import br.com.javahome.model.usuario.UsuarioLogado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioLogadoTeste {

	private UsuarioLogado usuarioLogado;
	
	private Usuario usuario;
	
	@BeforeEach
	public void setUp() {
		usuarioLogado = new UsuarioLogado();
		usuario = new Usuario();
	}
	
	@Test
	public void testaUsuarioLogado() {
		//cenario
		String nome = "Teste";
		String cpf= "35545212524";
		String email= "teste@teste.com.br";
		
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		
		//quando
		usuarioLogado.setUsuario(usuario);
		
		//então
		assertNotNull(usuarioLogado.getUsuario());
		assertEquals(nome, usuarioLogado.getUsuario().getNome());
		assertEquals(cpf, usuarioLogado.getUsuario().getCpf());
		assertEquals(email, usuarioLogado.getUsuario().getEmail());
	}

	@Test
	public void deveRetornarCep() {
		//Cenário
		String nome = "Teste";
		String cpf= "35545212524";
		String email= "teste@teste.com.br";

		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setEmail(email);

		Endereco endereco = new Endereco();
		endereco.setCep("04620156");
		endereco.setLogradouro("rua teste");

		usuario.setEndereco(Arrays.asList(endereco));

		//Quando
		List<Endereco> lista = usuario.getEndereco();

		//Então
		assertFalse(lista.isEmpty());
		assertEquals("rua teste", lista.get(0).getLogradouro());
	}

	@Test
	public void deveRetornarNull() {
		//Cenário
		String nome = "Teste";
		String cpf= "35545212524";
		String email= "teste@teste.com.br";

		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setEmail(email);

		//Quando
		assertNull(usuario.getEndereco());
	}

}

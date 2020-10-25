package br.com.javahome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.javahome.model.enums.TipoUsuario;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Size(min = 5, max = 50)
	@NotNull
	@Column(name = "nome")
	private String nome;
	
	@CPF(message="cpf inválido")
	private String cpf;
	
	@NotNull
	@Email(regexp = "^(.+)@(.+)$")
	@Column(name = "email",unique = true)
	private String email;

	@NotNull
	@Column(name = "senha")
	private String senha;
	
	@NotNull
	@Column(name = "cargo")
	private String cargo;
		
	private TipoUsuario TipoUsuario;
	
	@NotNull
	@Column(name="status")
	private boolean status = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}

	public void getUsuarioLogado(HttpSession session) {
		this.id = session.getAttribute("usuario.id") != null ? (int) session.getAttribute("usuario.id") : 0;
		this.nome = (String) session.getAttribute("usuario.nome");
		this.email = (String) session.getAttribute("usuario.email");
		this.status = session.getAttribute("usuario.ativo") != null ? (boolean) session.getAttribute("usuario.status")
				: false;
		
	}

	public void setUsuarioLogado(HttpSession session) {
		session.setAttribute("usuario.id", this.id);
		session.setAttribute("usuario.nome", this.nome);
		session.setAttribute("usuario.cpf", this.email);
		session.setAttribute("usuario.status", this.status);
		
	}

	public Usuario() {};


	
	
	
	
}

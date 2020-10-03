package br.com.javahome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name="status")
	private boolean status;

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



	
	
	
	
}

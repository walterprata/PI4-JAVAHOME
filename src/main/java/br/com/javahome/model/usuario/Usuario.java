package br.com.javahome.model.usuario;

import br.com.javahome.model.Endereco;
import br.com.javahome.model.enums.TipoUsuario;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


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

	@Column(name = "cpf",unique = true)
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

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Endereco> endereco;

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

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public boolean isStatus() {
		return status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return id == usuario.id &&
				Objects.equals(cpf, usuario.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpf);
	}
}

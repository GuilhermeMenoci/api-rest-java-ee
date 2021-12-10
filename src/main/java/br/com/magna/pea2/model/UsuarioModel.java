package br.com.magna.pea2.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String senha;
	@OneToOne(fetch = FetchType.EAGER)
	private EventoModel evento;

	public UsuarioModel() {
	}

	public UsuarioModel(Long id, String login, String senha, EventoModel evento) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.evento = evento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EventoModel getEvento() {
		return evento;
	}

	public void setEvento(EventoModel evento) {
		this.evento = evento;
	}

	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", login=" + login + ", senha=" + senha + ", evento=" + evento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(evento, id, login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioModel other = (UsuarioModel) obj;
		return Objects.equals(evento, other.evento) && Objects.equals(id, other.id)
				&& Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}

}

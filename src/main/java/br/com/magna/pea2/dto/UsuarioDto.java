package br.com.magna.pea2.dto;

import java.util.Objects;

import br.com.magna.pea2.model.EventoModel;

public class UsuarioDto {
	
	private String login;
	private String senha;
	private EventoModel evento;

	public UsuarioDto() {
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

	public void setEvento(EventoModel evento) {
		this.evento = evento;
	}

	public EventoModel getEvento() {
		return evento;
	}
	
	@Override
	public String toString() {
		return "UsuarioDto [login=" + login + ", senha=" + senha + ", evento=" + evento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(evento, login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDto other = (UsuarioDto) obj;
		return Objects.equals(evento, other.evento) 
				&& Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}

}

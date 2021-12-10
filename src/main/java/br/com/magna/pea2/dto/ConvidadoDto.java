package br.com.magna.pea2.dto;

import java.io.Serializable;
import java.util.Objects;

import br.com.magna.pea2.model.EventoModel;

public class ConvidadoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String nome;
	private EventoModel evento;
	
	public ConvidadoDto() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EventoModel getEvento() {
		return evento;
	}

	public void setEvento(EventoModel evento) {
		this.evento = evento;
	}
	
	@Override
	public String toString() {
		return "ConvidadoDto [cpf=" + cpf + ", nome=" + nome + ", evento=" + evento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, evento, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConvidadoDto other = (ConvidadoDto) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(evento, other.evento)
				&& Objects.equals(nome, other.nome);
	}
	
}

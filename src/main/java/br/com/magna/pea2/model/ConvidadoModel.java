package br.com.magna.pea2.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "convidado")
public class ConvidadoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String nome;
	@ManyToOne
	private EventoModel evento;

	public ConvidadoModel() {
	}

	public ConvidadoModel(Long id, String cpf, String nome, EventoModel evento) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.evento = evento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public EventoModel getEvento() {
		return evento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEvento(EventoModel evento) {
		this.evento = evento;
	}

	@Override
	public String toString() {
		return "ConvidadoModel [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", evento=" + evento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, evento, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConvidadoModel other = (ConvidadoModel) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(evento, other.evento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
}

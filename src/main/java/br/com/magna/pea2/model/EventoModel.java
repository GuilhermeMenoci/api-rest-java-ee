package br.com.magna.pea2.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "evento")
public class EventoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long codigo;
	private String nomeEvento;
	private LocalDate data = LocalDate.now();
	private String cidade;

	public EventoModel() {
	}

	public EventoModel(Long id, Long codigo, String nomeEvento, String cidade) {
		this.id = id;
		this.codigo = codigo;
		this.nomeEvento = nomeEvento;
		this.cidade = cidade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public String getCidade() {
		return cidade;
	}

	@Override
	public String toString() {
		return "EventoModel [id=" + id + ", codigo=" + codigo + ", nomeEvento=" + nomeEvento + ", data=" + data
				+ ", cidade=" + cidade + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cidade, codigo, data, id, nomeEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoModel other = (EventoModel) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(data, other.data) && Objects.equals(id, other.id)
				&& Objects.equals(nomeEvento, other.nomeEvento);
	}

}

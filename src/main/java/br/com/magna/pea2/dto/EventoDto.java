package br.com.magna.pea2.dto;

import java.time.LocalDate;

public class EventoDto {
	private Long codigo;
	private String nomeEvento;
	private LocalDate data = LocalDate.now();
	private String cidade;

	public EventoDto() {
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public String getNomeEvento() {
		return nomeEvento;
	}

	public String getCidade() {
		return cidade;
	}

	@Override
	public String toString() {
		return "EventoDto [codigo=" + codigo + ", nomeEvento=" + nomeEvento + ", data=" + data + ", cidade=" + cidade
				+ "]";
	}
}

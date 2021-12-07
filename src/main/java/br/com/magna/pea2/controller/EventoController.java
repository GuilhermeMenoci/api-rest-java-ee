package br.com.magna.pea2.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.magna.pea2.dto.EventoDto;
import br.com.magna.pea2.model.EventoModel;
import br.com.magna.pea2.service.EventoService;

@Path("/eventos")
public class EventoController {

	@Inject
	private EventoService eventoService;

	// Adicionando eventos
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvar(EventoModel evento) {
		try {
			eventoService.salvarEventoDao(evento);
			return Response.ok().build();
		} catch (NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}

	// Listando todos os eventos
	@GET
	// Utilizada para informar qual o formato/MediaType (XML, JSON, TEXT etc...)
	// será devolvido ao cliente (GET)
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventoDto> listarTodos() {
		try {
			return eventoService.listarEventos();
		} catch (NotFoundException ex) {
			ex.getMessage();
			return eventoService.listarEventos();
		}
	}

	// Listando evento por Codigo
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public EventoDto buscarCodigo(@PathParam("codigo") Long codigo) {
		try {
			return eventoService.buscarCodigo(codigo);
		} catch(NotFoundException ex) {
			ex.getMessage();
			return eventoService.buscarCodigo(codigo);
		}
		
	}
	
//	@PUT
//	@Transactional
//	@Path("/{codigo}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	//@Produces(MediaType.APPLICATION_JSON)
//	public Response atualizar(@PathParam("codigo") Long codigo, EventoDto eventoDto) {
//		try {
//			EventoDto dto = eventoService.atualizar(eventoDto, codigo);	
//			return Response.ok(dto).build();
//		} catch(NotFoundException ex) {
//			ex.getMessage();
//			return Response.noContent().build();
//		}
//		
//	}

	@DELETE
	@Transactional
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(@PathParam("codigo") Long codigo) {
		try {
			eventoService.deletar(codigo);
			return Response.ok().build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		}	
	}


//	@GET
//	//Qual o formato/MediaType que será utilizado para enviar os dados para o servidor
//	//@Consumes(MediaType.APPLICATION_JSON)
//	//Utilizada para informar qual o formato/MediaType (XML, JSON, TEXT etc...) será devolvido ao cliente (GET)
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<EventoDto> listarTodos(){
//		try {
//			return eventoService.listarEventos();
//		} catch(NotFoundException ex) {
//			ex.getMessage();
//			return null;
//		}
//	}

}

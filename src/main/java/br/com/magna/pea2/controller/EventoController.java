package br.com.magna.pea2.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.magna.pea2.dto.EventoDto;
import br.com.magna.pea2.service.EventoService;

@Path("/eventos")
public class EventoController {

	@Inject
	private EventoService eventoService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvento(EventoDto evento) {
		try {
			eventoService.createEventoDto(evento);
			return Response.ok().build();
		} catch (IllegalArgumentException ex) {
			ex.getMessage();
			return Response.noContent().build();
		} catch (Exception ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventoDto> listAllEventos() {
		try {
			return eventoService.listEvent();
		} catch (NotFoundException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public EventoDto listCodigo(@PathParam("codigo") Long codigo) {
		try {
			return eventoService.getCodigo(codigo);
		} catch (NotFoundException ex) {
			ex.getMessage();
			return eventoService.getCodigo(codigo);
		} catch(Exception ex) {
			ex.getMessage();
			return null;
		}
	}

	@PUT
	@Transactional
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("codigo") Long codigo, EventoDto eventoDto) {
		try {
			EventoDto dto = eventoService.update(codigo, eventoDto);
			return Response.ok(dto).build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		} catch(Exception ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}

	@DELETE
	@Transactional
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvento(@PathParam("codigo") Long codigo) {
		try {
			eventoService.delete(codigo);
			return Response.ok().build();
		} catch (NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		} catch(Exception ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}

}

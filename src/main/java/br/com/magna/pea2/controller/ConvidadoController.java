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

import br.com.magna.pea2.dto.ConvidadoDto;
import br.com.magna.pea2.model.ConvidadoModel;
import br.com.magna.pea2.service.ConvidadoService;

@Path("/convidados")
public class ConvidadoController {

	@Inject
	private ConvidadoService convidadoService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createConvidado(ConvidadoModel convidado) {
		try {
			convidadoService.createGuestsDto(convidado);
			return Response.ok().build();
		} catch (NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConvidadoDto> listAllGuests() {
		try {
			return convidadoService.listGuests();
		} catch (NotFoundException ex) {
			ex.getMessage();
		} catch(Exception ex) {
			ex.getMessage();
		}
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{cpf}")
	public ConvidadoDto listCpf(@PathParam("cpf") String cpf) {
		try {
			return convidadoService.getCpf(cpf);
		} catch(NotFoundException ex) {
			ex.getMessage();
		} catch(Exception ex) {
			ex.getMessage();
		}
		return null;
	}
	
	@PUT
	@Transactional
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cpf") String cpf, ConvidadoDto convidadoDto) {
		try {
			ConvidadoDto dto = convidadoService.update(cpf, convidadoDto);
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
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("cpf") String cpf) {
		try {
			convidadoService.delete(cpf);
			return Response.ok().build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		} catch(Exception ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}

}

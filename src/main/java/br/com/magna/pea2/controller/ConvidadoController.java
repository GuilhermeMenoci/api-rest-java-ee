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

import br.com.magna.pea2.dto.ConvidadoDto;
import br.com.magna.pea2.model.ConvidadoModel;
import br.com.magna.pea2.service.ConvidadoService;

@Path("/convidados")
public class ConvidadoController {

	@Inject
	private ConvidadoService convidadoService;

	// Adicionando convidados
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvar(ConvidadoModel convidado) {
		try {
			convidadoService.salvarConvidadoDao(convidado);
			return Response.ok().build();
		} catch (NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}

	// Listando todos os convidados
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConvidadoDto> listarTodos() {
		try {
			return convidadoService.listarConvidados();
		} catch (NotFoundException ex) {
			ex.getMessage();
			return convidadoService.listarConvidados();
		}
	}
	
	// Listando convidado por CPF
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{cpf}")
	public ConvidadoDto buscarCpf(@PathParam("cpf") String cpf) {
		try {
			return convidadoService.buscarCpf(cpf);
		} catch(NotFoundException ex) {
			ex.getMessage();
			return convidadoService.buscarCpf(cpf);
		}	
	}
	
	//Deletando convidado por CPF
	@DELETE
	@Transactional
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(@PathParam("cpf") String cpf) {
		try {
			convidadoService.deletar(cpf);
			return Response.ok().build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		}	
	}

}

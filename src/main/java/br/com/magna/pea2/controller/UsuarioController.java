package br.com.magna.pea2.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
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

import br.com.magna.pea2.dto.UsuarioDto;
import br.com.magna.pea2.model.UsuarioModel;
import br.com.magna.pea2.service.UsuarioService;

@Path("/usuarios")
public class UsuarioController {

	@Inject
	private UsuarioService usuarioService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UsuarioModel usuario) {
		try {
			usuarioService.createUserDto(usuario);
			return Response.ok().build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		} catch(IllegalArgumentException ex) {
			ex.getMessage();
			return Response.noContent().build();
		} catch(Exception ex) {
			ex.getMessage();
			return null;
		}
	}
	
	// Listando todos os usuarios
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioDto> listAllUser() {
		try {
			return usuarioService.listUser();
		} catch (NotFoundException ex) {
			ex.getMessage();
		} catch(Exception ex) {
			ex.getMessage();
		}
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{login}")
	public UsuarioDto listLogin(@PathParam("login") String login) {
		try {
			return usuarioService.getLogin(login);
		} catch(NotFoundException ex) {
			ex.getMessage();
		} catch(EntityNotFoundException ex) {
			ex.getMessage();
		} catch(Exception ex) {
			ex.getMessage();
		}
		return null;
	}
	
	@PUT
	@Transactional
	@Path("/{login}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("login") String login, UsuarioDto usuarioDto) {
		try {
			UsuarioDto dto = usuarioService.update(login, usuarioDto);
			return Response.ok(dto).build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		} catch(Exception ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}
	
	//Deletando usuario por Login
	@DELETE
	@Transactional
	@Path("/{login}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("login") String login) {
		try {
			usuarioService.delete(login);
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

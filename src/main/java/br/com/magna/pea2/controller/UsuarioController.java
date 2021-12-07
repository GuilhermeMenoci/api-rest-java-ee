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

import br.com.magna.pea2.dto.UsuarioDto;
import br.com.magna.pea2.model.UsuarioModel;
import br.com.magna.pea2.service.UsuarioService;

@Path("/usuarios")
public class UsuarioController {

	@Inject
	private UsuarioService usuarioService;
	
	//Adicionando usuario
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvar(UsuarioModel usuario) {
		try {
			usuarioService.salvarUsuarioDao(usuario);
			return Response.ok().build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		}
	}
	
	// Listando todos os usuarios
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioDto> listarTodos() {
		try {
			return usuarioService.listarUsuarios();
		} catch (NotFoundException ex) {
			ex.getMessage();
			return usuarioService.listarUsuarios();
		}
	}
	
	// Listando usuario por Login
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{login}")
	public UsuarioDto buscarLogin(@PathParam("login") String login) {
		try {
			return usuarioService.buscarLogin(login);
		} catch(NotFoundException ex) {
			ex.getMessage();
			return usuarioService.buscarLogin(login);
		}
		
	}
	
	//Deletando usuario por Login
	@DELETE
	@Transactional
	@Path("/{login}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(@PathParam("login") String login) {
		try {
			usuarioService.deletar(login);
			return Response.ok().build();
		} catch(NotFoundException ex) {
			ex.getMessage();
			return Response.noContent().build();
		}	
	}
	
	
}

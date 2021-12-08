package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.magna.pea2.dao.UsuarioDao;
import br.com.magna.pea2.dto.UsuarioDto;
import br.com.magna.pea2.model.UsuarioModel;

@Named
public class UsuarioService {
	
	private static Logger logger = LoggerFactory.getLogger(EventoService.class);

	@Inject
	private UsuarioDao usuarioDao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	//Adicionando usuario
	public UsuarioModel createUserDto(UsuarioModel usuario) throws IllegalArgumentException{
		try {
			UsuarioModel usuarioSalvo = usuarioDao.create(usuario);
			logger.info("Usuario cadastrado com sucesso");
			return usuarioSalvo;
		} catch(IllegalArgumentException ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
	}
	
	// Listando todos os usuarios
	public List<UsuarioDto> listUser() throws Exception{
		try {
			List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>();
			List<UsuarioModel> usuarioModel = usuarioDao.list();
			for (UsuarioModel user : usuarioModel) {
				usuarios.add(modelMapper.map(user, UsuarioDto.class));
			}
			logger.info("Usuarios listados");
			return usuarios;
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
	}
	
	// Listando usuario por Login
	public UsuarioDto getLogin(String login) throws Exception{
		try {
			UsuarioDto usuarioDto = new UsuarioDto();
			UsuarioModel usuarioModel = usuarioDao.getLogin(login);
			usuarioDto = modelMapper.map(usuarioModel, UsuarioDto.class);
			logger.info("Usuario com login: " + "'" +  login + "'" + " listado");
			return usuarioDto;
		} catch(Exception ex) {
			logger.error("Não existe esse usuario com login: " + "'" + login + "'");
			throw ex;
		}
	}
	
	//Atualizando usuario
	public UsuarioDto update(String login, UsuarioDto usuarioDto) throws EntityNotFoundException {
		try {
//			if (!this.eventoDao.getCodigo(eventoDto.getCodigo())) {
//				throw new NotFoundException("The cpf refered does not match with any client");
//			}
			UsuarioModel model = usuarioDao.getLogin(login);
			model.setLogin(usuarioDto.getLogin());
			model.setSenha(usuarioDto.getSenha());
			model.setEvento(usuarioDto.getEvento());
			usuarioDao.update(model);
			UsuarioDto dto = modelMapper.map(model, UsuarioDto.class);
			logger.info("Usuario com login: " + "'" + login + "'" + " atualizado");
			return dto;
		} catch (EntityNotFoundException ex) {
			logger.error("Não existe esse usuario com login: " + "'" + login + "'");
			throw ex;
		}
	}
	
	//Deletando usuario por Login
	public void delete(String login) throws Exception{
		try {
			UsuarioModel model = usuarioDao.getLogin(login);
			usuarioDao.delete(model);
			logger.info("Usuario com login: " + "'" + login + "'" + " deletado");
		} catch(Exception ex) {
			logger.error("Usuario não encontrado/deletado");
		}
	}
	
}

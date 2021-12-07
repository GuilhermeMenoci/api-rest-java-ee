package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import br.com.magna.pea2.dao.UsuarioDao;
import br.com.magna.pea2.dto.UsuarioDto;
import br.com.magna.pea2.model.UsuarioModel;

@Named
public class UsuarioService {

	@Inject
	private UsuarioDao usuarioDao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public UsuarioModel salvarUsuarioDao(UsuarioModel usuario) {
		UsuarioModel usuarioSalvo = usuarioDao.salvar(usuario);
		return usuarioSalvo;
	}
	
	public List<UsuarioDto> listarUsuarios() {
		List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>();
		List<UsuarioModel> usuarioModel = usuarioDao.listar();
		for (UsuarioModel user : usuarioModel) {
			usuarios.add(modelMapper.map(user, UsuarioDto.class));
		}
		return usuarios;
	}
	
	public UsuarioDto buscarLogin(String login) {
		UsuarioDto usuarioDto = new UsuarioDto();
		UsuarioModel usuarioModel = usuarioDao.pegarLogin(login);
		usuarioDto = modelMapper.map(usuarioModel, UsuarioDto.class);
		return usuarioDto;
	}
	
	public void deletar(String login) {
		UsuarioModel model = usuarioDao.pegarLogin(login);
		usuarioDao.deletar(model);
	}
	
}

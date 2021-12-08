package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import br.com.magna.pea2.dao.ConvidadoDao;
import br.com.magna.pea2.dto.ConvidadoDto;
import br.com.magna.pea2.model.ConvidadoModel;

@Named
public class ConvidadoService {

	@Inject
	private ConvidadoDao convidadoDao;
	private ModelMapper modelMapper = new ModelMapper();
	
	public ConvidadoModel salvarConvidadoDao(ConvidadoModel convidado) {
		ConvidadoModel convidadoSalvo = convidadoDao.salvar(convidado);
		return convidadoSalvo;
	}
	
	public List<ConvidadoDto> listarConvidados() {
		List<ConvidadoDto> convidados = new ArrayList<ConvidadoDto>();
		List<ConvidadoModel> convidadoModel = convidadoDao.listar();
		for (ConvidadoModel convid : convidadoModel) {
			convidados.add(modelMapper.map(convid, ConvidadoDto.class));
		}
		return convidados;
	}
	
	public ConvidadoDto buscarCpf(String cpf) {
		ConvidadoDto convidadoDto = new ConvidadoDto();
		ConvidadoModel convidadoModel = convidadoDao.pegarCpf(cpf);
		convidadoDto = modelMapper.map(convidadoModel, ConvidadoDto.class);
		return convidadoDto;
	}
	
	public void deletar(String cpf) {
		ConvidadoModel model = convidadoDao.pegarCpf(cpf);
		convidadoDao.deletar(model);
	}
	
}

package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;

import br.com.magna.pea2.dao.ConvidadoDao;
import br.com.magna.pea2.dto.ConvidadoDto;
import br.com.magna.pea2.model.ConvidadoModel;

@Named
public class ConvidadoService {

	@Inject
	private ConvidadoDao convidadoDao;
	private ModelMapper modelMapper = new ModelMapper();
	
	public ConvidadoModel createGuestsDto(ConvidadoModel convidado) {
		ConvidadoModel convidadoSalvo = convidadoDao.create(convidado);
		return convidadoSalvo;
	}
	
	public List<ConvidadoDto> listGuests() {
		List<ConvidadoDto> convidados = new ArrayList<ConvidadoDto>();
		List<ConvidadoModel> convidadoModel = convidadoDao.list();
		for (ConvidadoModel convid : convidadoModel) {
			convidados.add(modelMapper.map(convid, ConvidadoDto.class));
		}
		return convidados;
	}
	
	public ConvidadoDto getCpf(String cpf) {
		ConvidadoDto convidadoDto = new ConvidadoDto();
		ConvidadoModel convidadoModel = convidadoDao.getCpf(cpf);
		convidadoDto = modelMapper.map(convidadoModel, ConvidadoDto.class);
		return convidadoDto;
	}
	
	//Atualizando convidado
	public ConvidadoDto update(String cpf, ConvidadoDto convidadoDto) throws EntityNotFoundException {
		try {
//			if (!this.eventoDao.getCodigo(eventoDto.getCodigo())) {
//				throw new NotFoundException("The cpf refered does not match with any client");
//			}
			ConvidadoModel model = convidadoDao.getCpf(cpf);
			model.setNome(convidadoDto.getNome());
			model.setCpf(convidadoDto.getCpf());
			model.setEvento(convidadoDto.getEvento());
			convidadoDao.update(model);
			ConvidadoDto dto = modelMapper.map(model, ConvidadoDto.class);
			//logger.info("Usuario com login: " + "'" + login + "'" + " atualizado");
			return dto;
		} catch (EntityNotFoundException ex) {
			//logger.error("Não existe esse usuario com login: " + "'" + login + "'");
			throw ex;
		}
	}
	
	public void delete(String cpf) {
		ConvidadoModel model = convidadoDao.getCpf(cpf);
		convidadoDao.delete(model);
	}
	
}

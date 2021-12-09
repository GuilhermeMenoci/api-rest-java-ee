package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.magna.pea2.dao.ConvidadoDao;
import br.com.magna.pea2.dto.ConvidadoDto;
import br.com.magna.pea2.model.ConvidadoModel;

@Named
public class ConvidadoService {
	
	private static Logger logger = LoggerFactory.getLogger(ConvidadoService.class);

	@Inject
	private ConvidadoDao convidadoDao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	// Adicionando convidados
	public ConvidadoModel createGuestsDto(ConvidadoModel convidado) throws IllegalArgumentException{
		try {
			ConvidadoModel convidadoSalvo = convidadoDao.create(convidado);
			logger.info("Convidado cadastrado com sucesso");
			return convidadoSalvo;
		} catch(IllegalArgumentException ex) {
			logger.error("Convidado não cadastrado");
			throw ex;
		}
	}
	
	// Listando todos os convidados
	public List<ConvidadoDto> listGuests() throws Exception{
		try {
			List<ConvidadoDto> convidados = new ArrayList<ConvidadoDto>();
			List<ConvidadoModel> convidadoModel = convidadoDao.list();
			for (ConvidadoModel convid : convidadoModel) {
				convidados.add(modelMapper.map(convid, ConvidadoDto.class));
			}
			logger.info("Convidados listados");
			return convidados; 
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
	}
	
	// Listando convidado por CPF
	public ConvidadoDto getCpf(String cpf) throws Exception{
		try {
			ConvidadoDto convidadoDto = new ConvidadoDto();
			ConvidadoModel convidadoModel = convidadoDao.getCpf(cpf);
			convidadoDto = modelMapper.map(convidadoModel, ConvidadoDto.class);
			logger.info("Convidado com CPF: " + "'" + cpf + "'" + " listado");
			return convidadoDto;
		} catch(Exception ex) {
			logger.error("Não existe esse convidado com CPF: " + "'" + cpf + "'");
			throw ex;
		}
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
			logger.info("Convidado com CPF: " + "'" + cpf + "'" + " atualizado");
			return dto;
		} catch (EntityNotFoundException ex) {
			logger.error("Não existe esse convidado com CPF: " + "'" + cpf + "'");
			throw ex;
		}
	}
	
	//Deletando convidado por CPF
	public void delete(String cpf) throws Exception{
		try {
			ConvidadoModel model = convidadoDao.getCpf(cpf);
			convidadoDao.delete(model);
			logger.info("Convidado com CPF: " + "'" + cpf + "'" + " deletado");
		} catch(Exception ex) {
			logger.error("Convidado não deletado/encontrado");
		}
	}
	
}

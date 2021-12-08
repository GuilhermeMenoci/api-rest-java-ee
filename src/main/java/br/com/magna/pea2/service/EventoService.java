package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.magna.pea2.dao.EventoDao;
import br.com.magna.pea2.dto.EventoDto;
import br.com.magna.pea2.model.EventoModel;

@Named
public class EventoService {

	private static Logger logger = LoggerFactory.getLogger(EventoService.class);
	
	@Inject
	private EventoDao eventoDao;

	private ModelMapper modelMapper = new ModelMapper();

//	// Cadastrando um evento
//	public EventoModel createEventoDto(EventoModel evento) throws IllegalArgumentException {
//		try {
//			if (verificaEvento(evento)) {
//				System.out.println("evento já cadastrado");
//			} else {
//				EventoModel eventoSalvo = eventoDao.create(evento);
//				return eventoSalvo;
//			}
//		} catch (IllegalArgumentException ex) {
//			ex.getMessage();
//		} catch(NotFoundException ex) {
//			ex.getMessage();
//		}
//		return null;
//	}

	// Cadastrando um evento
	public EventoModel createEventoDto(EventoModel evento) throws IllegalArgumentException {
		try {
			EventoModel eventoSalvo = eventoDao.create(evento);
			logger.info("Evento cadastrado com sucesso");
			return eventoSalvo;
		} catch (IllegalArgumentException ex) {
			logger.error("Erro, não foi possivel cadastrar evento");
			throw ex;
		}
	}

//	// Verificando se o evento já tem um cadastro
//	public Boolean verificaEvento(EventoModel codigoEvento) {
//		Boolean verificaEvento = false;
//		verificaEvento = eventoDao.existsCodigo(codigoEvento.getCodigo());
//		return verificaEvento;
//	}

	// Listando todos os eventos
	public List<EventoDto> listEvent() throws NotFoundException {
		try {
			List<EventoDto> eventos = new ArrayList<EventoDto>();
			List<EventoModel> eventoModel = eventoDao.list();
			for (EventoModel event : eventoModel) {
				eventos.add(modelMapper.map(event, EventoDto.class));
			}
			logger.info("Eventos listados");
			return eventos;
		} catch (NotFoundException ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
	}

	// Listando evento por Codigo
	public EventoDto getCodigo(Long codigo) throws NotFoundException {
		try {
			EventoDto eventoDto = new EventoDto();
			EventoModel eventoModel = eventoDao.getCodigo(codigo);
			eventoDto = modelMapper.map(eventoModel, EventoDto.class);
			logger.info("Evento com código: " + codigo + " listado");
			return eventoDto;
		} catch (NotFoundException ex) {
			logger.error("Não existe esse evento com código: " + "'" + codigo + "'");
			throw ex;
		}
	}
	
	//Atualizando evento
	public EventoDto update(Long codigo, EventoDto eventoDto) throws Exception {
		try {
//			if (!this.eventoDao.getCodigo(eventoDto.getCodigo())) {
//				throw new NotFoundException("The cpf refered does not match with any client");
//			}
			EventoModel model = eventoDao.getCodigo(codigo);
			model.setCodigo(eventoDto.getCodigo());
			model.setCidade(eventoDto.getCidade());
			model.setNomeEvento(eventoDto.getNomeEvento());
			model.setData(eventoDto.getData());
			eventoDao.atualizar(model);
			EventoDto dto = modelMapper.map(model, EventoDto.class);
			logger.info("Evento com código: " + codigo + " atualizado");
			return dto;
		} catch (Exception ex) {
			logger.error("Não existe esse evento com código: " + "'" + codigo + "'");
			throw ex;
		}
	}

	// Deletando evento por Codigo
	public void delete(Long codigo) throws NotFoundException {
		try {
			EventoModel model = eventoDao.getCodigo(codigo);
			eventoDao.delete(model);
			logger.info("Evento com código: " + "'" +codigo + "'" + " deletado");
		} catch (NotFoundException ex) {
			logger.error("Evento não encontrado/deletado");
		}
	}

	public EventoModel convertModel(EventoDto eventoDto) {
		return modelMapper.map(eventoDto, EventoModel.class);
	}

	public EventoDto convertDto(EventoModel eventoModel) {
		return modelMapper.map(eventoModel, EventoDto.class);
	}

}

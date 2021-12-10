package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
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
	
	// Cadastrando um evento
		public EventoDto createEventoDto(EventoDto evento) throws IllegalArgumentException {
			try {
				EventoModel model = modelMapper.map(evento, EventoModel.class);
				EventoModel eventoSalvo = eventoDao.create(model);
				logger.info("Evento cadastrado com sucesso");
				return modelMapper.map(eventoSalvo, EventoDto.class);
			} catch (IllegalArgumentException ex) {
				logger.error("Erro, não foi possivel cadastrar evento");
				throw ex;
			}
		}

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
		} catch(Exception ex) {
			logger.error("Não existe esse vento com código: " + "'" + codigo + "'");
			throw ex;
		}
	}
	
	//Atualizando evento
	public EventoDto update(Long codigo, EventoDto eventoDto) throws EntityNotFoundException {
		try {
			if(codigo == null) {
				EventoModel model = eventoDao.getCodigo(codigo);
				model.setCodigo(eventoDto.getCodigo());
				model.setCidade(eventoDto.getCidade());
				model.setNomeEvento(eventoDto.getNomeEvento());
				model.setData(eventoDto.getData());
				eventoDao.atualizar(model);
				EventoDto dto = modelMapper.map(model, EventoDto.class);
				logger.info("Evento com código: " + codigo + " atualizado");
				return dto;
			} else {
				logger.info("Evento com código: " + codigo + " não encontrado");
				return eventoDto;
			}
		} catch (EntityNotFoundException ex) {
			logger.error("Não existe esse convidado com CPF: " + "'" + codigo + "'");
			throw ex;
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
		} catch(Exception ex) {
			logger.error("Evento não encontrado/deletado");
		}
	}

}

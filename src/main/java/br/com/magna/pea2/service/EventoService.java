package br.com.magna.pea2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import br.com.magna.pea2.dao.EventoDao;
import br.com.magna.pea2.dto.EventoDto;
import br.com.magna.pea2.model.EventoModel;

@Named
public class EventoService {

	@Inject
	private EventoDao eventoDao;

	private ModelMapper modelMapper = new ModelMapper();

	// private ModelMapper modelMapper = new ModelMapper();

	public EventoModel salvarEventoDao(EventoModel evento) {
		EventoModel eventoSalvo = eventoDao.salvar(evento);
		return eventoSalvo;
	}

	public List<EventoDto> listarEventos() {
		List<EventoDto> eventos = new ArrayList<EventoDto>();
		List<EventoModel> eventoModel = eventoDao.listar();
		for (EventoModel event : eventoModel) {
			eventos.add(modelMapper.map(event, EventoDto.class));
		}
		return eventos;
	}

	public EventoDto buscarCodigo(Long codigo) {
		EventoDto eventoDto = new EventoDto();
		EventoModel eventoModel = eventoDao.pegarCodigo(codigo);
		eventoDto = modelMapper.map(eventoModel, EventoDto.class);
		return eventoDto;
	}

//	public EventoDto atualizar(EventoDto eventoDto, Long codigo) {
//		EventoModel evento = eventoDao.pegarCodigo(codigo);
//		EventoDto eventoDtoAntigo = convertDto(evento);
//		eventoDto = eventoDtoAntigo;
//		EventoModel convertModel = convertModel(eventoDto);
//		convertModel.setId(evento.getId());
//		EventoModel eventoAtualizado = eventoDao.atualizar(convertModel);
//		return convertDto(eventoAtualizado);
//	}
	
	public void deletar(Long codigo) {
		EventoModel model = eventoDao.pegarCodigo(codigo);
		eventoDao.deletar(model);
	}


	public EventoModel convertModel(EventoDto eventoDto) {
		return modelMapper.map(eventoDto, EventoModel.class);
	}
	
	public EventoDto convertDto(EventoModel eventoModel) {
		return modelMapper.map(eventoModel, EventoDto.class);
	}

}

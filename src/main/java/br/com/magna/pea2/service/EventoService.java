package br.com.magna.pea2.service;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.magna.pea2.dao.EventoDao;
import br.com.magna.pea2.model.EventoModel;

@Named
public class EventoService {

	@Inject
	private EventoDao eventoDao;
	
	//private ModelMapper modelMapper = new ModelMapper();
	
	public EventoModel salvarEventoDao(EventoModel evento) {
		EventoModel eventoSalvo = eventoDao.save(evento);
		return eventoSalvo;
	}
	
//	public void add(EventoDto eventoDto) {
//		EventoModel eventoModel = modelMapper.map(eventoDto, EventoModel.class);
//		eventoDao.post(eventoModel);
//		eventoDto = modelMapper.map(eventoModel, EventoDto.class);
//	}
	
}

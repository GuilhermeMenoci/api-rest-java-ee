package br.com.magna.pea2.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.magna.pea2.model.EventoModel;

@Stateless
public class EventoDao {

	@PersistenceContext(unitName = "PostgresqlDS")
	private EntityManager em;
	
	public EventoModel save(EventoModel evento) {
		em.persist(evento);
		return evento;
	}
	
//	public void post(EventoModel model) {
//		em.persist(model);
//	}
	
}

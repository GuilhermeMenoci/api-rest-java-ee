package br.com.magna.pea2.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.magna.pea2.model.EventoModel;

@Stateless
public class EventoDao {

	@PersistenceContext(unitName = "PostgresqlDS")
	private EntityManager em;
	
	public EventoModel create(EventoModel evento) {
		em.persist(evento);
		return evento;
	}
	
	public List<EventoModel> list(){
		return em.createQuery(
				"SELECT e FROM EventoModel e", EventoModel.class)
				.getResultList();
	}
	
	public EventoModel getCodigo(Long codigo) {
		return em.createQuery(
				"SELECT e FROM EventoModel e WHERE e.codigo = :codigo", EventoModel.class)
				.setParameter("codigo", codigo).getSingleResult();
	}
	
//	public Boolean existsCodigo(Long codigo) {
//		 EventoModel result = em.createQuery(
//				"SELECT e FROM EventoModel e WHERE e.codigo = :codigo", EventoModel.class)
//				.setParameter("codigo", codigo).getSingleResult();
//		 return true;
//	}
	
	public EventoModel atualizar(EventoModel evento) {
		evento = em.find(EventoModel.class, evento.getId());
		return em.merge(evento);
	}
	
	public void delete(EventoModel evento) {
		evento = em.find(EventoModel.class, evento.getId());
		em.remove(evento);
	}
	
}

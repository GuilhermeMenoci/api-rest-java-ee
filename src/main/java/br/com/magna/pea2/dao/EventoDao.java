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
	
	public EventoModel salvar(EventoModel evento) {
		em.persist(evento);
		return evento;
	}
	
	public List<EventoModel> listar(){
		return em.createQuery(
				"SELECT e FROM EventoModel e", EventoModel.class)
				.getResultList();
	}
	
	public EventoModel pegarCodigo(Long codigo) {
		return em.createQuery(
				"SELECT e FROM EventoModel e WHERE e.codigo = :codigo", EventoModel.class)
				.setParameter("codigo", codigo).getSingleResult();
	}
	
//	public EventoModel atualizar(EventoModel model) {
//		model = em.find(EventoModel.class, model.getId());
//		return em.merge(model);
//	}
	
	public void deletar(EventoModel evento) {
		evento = em.find(EventoModel.class, evento.getId());
		em.remove(evento);
	}
	
}

package br.com.magna.pea2.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.magna.pea2.model.ConvidadoModel;

@Stateless
public class ConvidadoDao {

	@PersistenceContext(unitName = "PostgresqlDS")
	private EntityManager em;
	
	public ConvidadoModel create(ConvidadoModel convidado) {
		em.persist(convidado);
		return convidado;
	}
	
	public List<ConvidadoModel> list(){
		return em.createQuery(
				"SELECT c FROM ConvidadoModel c", ConvidadoModel.class)
				.getResultList();
	}
	
	public ConvidadoModel getCpf(String cpf) {
		return em.createQuery(
				"SELECT c FROM ConvidadoModel c WHERE c.cpf = :cpf", ConvidadoModel.class)
				.setParameter("cpf", cpf).getSingleResult();
	}
	
	public ConvidadoModel update(ConvidadoModel convidado) {
		convidado = em.find(ConvidadoModel.class, convidado.getId());
		return em.merge(convidado);
	}
	
	public void delete(ConvidadoModel convidado) {
		convidado = em.find(ConvidadoModel.class, convidado.getId());
		em.remove(convidado);
	}
	
}

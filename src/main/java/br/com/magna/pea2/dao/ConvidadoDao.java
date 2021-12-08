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
	
	public ConvidadoModel salvar(ConvidadoModel convidado) {
		em.persist(convidado);
		return convidado;
	}
	
	public List<ConvidadoModel> listar(){
		return em.createQuery(
				"SELECT c FROM ConvidadoModel c", ConvidadoModel.class)
				.getResultList();
	}
	
	public ConvidadoModel pegarCpf(String cpf) {
		return em.createQuery(
				"SELECT c FROM ConvidadoModel c WHERE c.cpf = :cpf", ConvidadoModel.class)
				.setParameter("cpf", cpf).getSingleResult();
	}
	
	public void deletar(ConvidadoModel convidado) {
		convidado = em.find(ConvidadoModel.class, convidado.getId());
		em.remove(convidado);
	}
	
}

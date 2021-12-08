package br.com.magna.pea2.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.magna.pea2.model.UsuarioModel;

@Stateless
public class UsuarioDao {

	@PersistenceContext(unitName = "PostgresqlDS")
	private EntityManager em;
	
	public UsuarioModel create(UsuarioModel usuario) {
		em.persist(usuario);
		return usuario;
	}
	
	public List<UsuarioModel> list(){
		return em.createQuery(
				"SELECT u FROM UsuarioModel u", UsuarioModel.class)
				.getResultList();
	}
	
	public UsuarioModel getLogin(String login) {
		return em.createQuery(
				"SELECT u FROM UsuarioModel u WHERE u.login = :login", UsuarioModel.class)
				.setParameter("login", login).getSingleResult();
	}
	
	public UsuarioModel update(UsuarioModel usuario) {
		usuario = em.find(UsuarioModel.class, usuario.getId());
		return em.merge(usuario);
	}
	
	public void delete(UsuarioModel usuario) {
		usuario = em.find(UsuarioModel.class, usuario.getId());
		em.remove(usuario);
	}
	
}

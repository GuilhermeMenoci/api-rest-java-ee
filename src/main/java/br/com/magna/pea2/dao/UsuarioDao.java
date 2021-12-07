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
	
	public UsuarioModel salvar(UsuarioModel usuario) {
		em.persist(usuario);
		return usuario;
	}
	
	public List<UsuarioModel> listar(){
		return em.createQuery(
				"SELECT u FROM UsuarioModel u", UsuarioModel.class)
				.getResultList();
	}
	
	public UsuarioModel pegarLogin(String login) {
		return em.createQuery(
				"SELECT u FROM UsuarioModel u WHERE u.login = :login", UsuarioModel.class)
				.setParameter("login", login).getSingleResult();
	}
	
	public void deletar(UsuarioModel usuario) {
		usuario = em.find(UsuarioModel.class, usuario.getId());
		em.remove(usuario);
	}
	
}

package br.com.reserveja.pessoa.dao;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.reserveja.model.domain.pessoa.Pessoa;
import br.com.reserveja.model.domain.telefone.Telefone;

@PersistenceUnit
@Transactional
@Repository
public class TelefoneDAOImpl extends GenericDao<Telefone, Serializable> {
	
	public List<Telefone> listarTelefonePessoa(Pessoa pessoa) {
		List<Telefone> listaTelefones = new ArrayList<>();
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Telefone> query = builder.createQuery(super.persistedClass);
		Root<Telefone> from = query.from(Telefone.class);
		TypedQuery<Telefone> typedQuery = entityManager.createQuery(
				query.select(from)
				.where(builder.gt(from.get("pessoa").get("id"), pessoa.getId()))
				);
		listaTelefones = typedQuery.getResultList();
		return listaTelefones;
	}
}

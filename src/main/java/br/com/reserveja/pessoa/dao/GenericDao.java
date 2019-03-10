package br.com.reserveja.pessoa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

@Component
@Transactional
@NoRepositoryBean
public abstract class GenericDao<T, I extends Serializable> {

	   
	   @Autowired
	   protected EntityManagerFactory entityManagerFactory;

	   @Autowired
	   protected EntityManager entityManager;
	   
	   @Autowired
	   private SessionFactory sessionFactory;
	   
	   protected Class<T> persistedClass;

	   protected GenericDao() {
	   }
	   
	   protected GenericDao(Class<T> persistedClass) {
	       this();
	       this.persistedClass = persistedClass;
	   }

	   public T salvar(@Valid T entity) {
	       EntityTransaction t = entityManager.getTransaction();
	       if(!t.isActive()) {
	    	   t.begin();
	       }
	       entityManager.persist(entity);
	       entityManager.flush();
	       t.commit();
	       return entity;
	   }

	   public T atualizar(@Valid T entity) {
	       EntityTransaction t = entityManager.getTransaction();
	       t.begin();
	       entityManager.merge(entity);
	       entityManager.flush();
	       t.commit();
	       return entity;
	   }

	   public void remover(I id) {
	       T entity = encontrar(id);
	       EntityTransaction tx = entityManager.getTransaction();
	       tx.begin();
	       T mergedEntity = entityManager.merge(entity);
	       entityManager.remove(mergedEntity);
	       entityManager.flush();
	       tx.commit();
	   }

	   public List<T> getList() {
		   CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	       CriteriaQuery<T> query = builder.createQuery(persistedClass);
	       query.from(persistedClass);
	       return entityManager.createQuery(query).getResultList();
	   }

	   public T encontrar(I id) {
	       T retorno = entityManager.find(persistedClass, id);
		   return retorno;
	   }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	   
}

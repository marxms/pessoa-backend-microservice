package br.com.reserveja.pessoa.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.reserveja.model.domain.endereco.Endereco;

@PersistenceUnit
@Transactional
@Repository
public class EnderecoDAOImpl extends GenericDao<Endereco , Serializable>{

	
	  public EnderecoDAOImpl() {
		super();

	}

	public Endereco encontrarPorEnderecoCompleto(Endereco endereco) {
		  CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	      CriteriaQuery<Endereco> query = builder.createQuery(Endereco.class);
		  Root<Endereco> rootEndereco = query.from(Endereco.class);
		  Predicate restricoes = builder.and(
				    builder.equal(rootEndereco.get("logradouro" ), endereco.getLogradouro()),
				    builder.equal(rootEndereco.get("cidade" ), endereco.getCidade()),
				    builder.equal(rootEndereco.get("estado" ), endereco.getEstado()),
				    builder.equal(rootEndereco.get("cep" ), endereco.getCep()),
				    builder.equal(rootEndereco.get("numero" ), endereco.getNumero()),
				    builder.equal(rootEndereco.get("complemento" ), endereco.getComplemento()));
		  query.where(builder.and(restricoes));
	      try {
	    	  endereco = entityManager.createQuery(query).getSingleResult();
	      } catch(NoResultException e) {
	    	  endereco = null;
	    	  e.printStackTrace();
	      }
   
		   return endereco;
	   }
	
	
}

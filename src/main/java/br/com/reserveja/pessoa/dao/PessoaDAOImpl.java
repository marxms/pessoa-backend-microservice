package br.com.reserveja.pessoa.dao;

import java.io.Serializable;

import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.reserveja.model.domain.endereco.Endereco;
import br.com.reserveja.model.domain.pessoa.Pessoa;
import br.com.reserveja.model.domain.telefone.Telefone;

@PersistenceUnit
@Transactional
@Repository
public class PessoaDAOImpl extends GenericDao<Pessoa, Serializable>{

	@Autowired
	TelefoneDAOImpl telefoneDAOImpl;
	
	@Autowired
	EnderecoDAOImpl enderecoDAOImpl;
	
	public PessoaDAOImpl() {
		super();
	}
	
	public Pessoa listarPessoaPorId(Pessoa pessoa) {
		pessoa = super.encontrar(pessoa.getId());
		pessoa.setTelefone(telefoneDAOImpl.listarTelefonePessoa(pessoa));
		pessoa.setEndereco(enderecoDAOImpl.encontrar(pessoa.getEndereco().getId()));		
		return pessoa;
		
	}
	
	public Pessoa cadastrarPessoa(Pessoa pessoa) {
		
		Endereco enderecoConsultado = enderecoDAOImpl.encontrarPorEnderecoCompleto(pessoa.getEndereco());
		
		if (enderecoConsultado == null) {
			enderecoDAOImpl.salvar(pessoa.getEndereco());
		} else {
			pessoa.setEndereco(enderecoConsultado);
			enderecoDAOImpl.salvar(pessoa.getEndereco());
		}
		pessoa = this.salvar(pessoa);
		
		for (Telefone telefone : pessoa.getTelefone()) {
			telefone.setPessoa(pessoa);
			telefone = telefoneDAOImpl.salvar(telefone);
		}
		return pessoa;
	}
	
}

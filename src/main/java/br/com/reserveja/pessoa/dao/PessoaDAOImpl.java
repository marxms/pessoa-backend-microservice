package br.com.reserveja.pessoa.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.reserveja.pessoa.model.endereco.Endereco;
import br.com.reserveja.pessoa.model.pessoa.Pessoa;
import br.com.reserveja.pessoa.model.telefone.Telefone;

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
		for (Telefone telefone : pessoa.getTelefone()) {
			telefone.setPessoa(pessoa);
			telefone = telefoneDAOImpl.salvar(telefone);
		}
		return pessoa;
	}
	
}

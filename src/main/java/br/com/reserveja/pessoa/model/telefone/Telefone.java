package br.com.reserveja.pessoa.model.telefone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.reserveja.pessoa.model.pessoa.Pessoa;

@Entity
public class Telefone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Integer DDD;
	
	private Integer numero;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoTelefone tpTelefone;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa pessoa;
	
	public Integer getDDD() {
		return DDD;
	}
	
	public void setDDD(Integer dDD) {
		DDD = dDD;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public EnumTipoTelefone getTpTelefone() {
		return tpTelefone;
	}
	public void setTpTelefone(EnumTipoTelefone tpTelefone) {
		this.tpTelefone = tpTelefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	
}

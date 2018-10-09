package br.com.reserveja.pessoa.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.reserveja.pessoa.model.pessoa.Pessoa;

public class TelefoneRepresentation{
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("ddd")
	private Integer DDD;
	
	@JsonProperty("numero")
	private Integer numero;
	
	@JsonProperty("tpTelefone")
	private String tpTelefone;
	
	@JsonProperty(access=Access.WRITE_ONLY, value="pessoa")
	private Pessoa pessoa;


	public void setId(Long id) {
		this.id = id;
	}

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

	public String getTpTelefone() {
		return tpTelefone;
	}

	public void setTpTelefone(String tpTelefone) {
		this.tpTelefone = tpTelefone;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

}

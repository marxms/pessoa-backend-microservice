package br.com.reserveja.pessoa.representation;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.authservice.dto.UserResponseDTO;


public class PessoaRepresentation{


	@JsonProperty(required = false, value="id")
	private Long id;
	
	@JsonProperty(required = false, value="nome")
	private String nome;
	
	@JsonProperty(required = false, value="sobrenome")
	private String sobrenome;
	
	@JsonProperty(required = false, value="dtnascimento")
	private Date dtnascimento;
	
	@JsonProperty(access=Access.READ_WRITE, value="endereco")
	private EnderecoRepresentation endereco;
	
	@JsonProperty(access=Access.READ_WRITE, value="telefone")
	private List<TelefoneRepresentation> telefone;
	
	@JsonProperty(required = false, value="email")
	private String email;
	
	@JsonProperty(required = false, value="sexo")
	private String sexo;
	
	@JsonProperty(access=Access.WRITE_ONLY, value="user")
	private UserResponseDTO user;




	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	/*public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}*/

	public String getEmail() {
		return email;
	}

	public EnderecoRepresentation getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRepresentation endereco) {
		this.endereco = endereco;
	}

	public List<TelefoneRepresentation> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<TelefoneRepresentation> telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public UserResponseDTO getUser() {
		return user;
	}

	public void setUser(UserResponseDTO user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

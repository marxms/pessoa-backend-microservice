package br.com.reserveja.pessoa.service.pessoa;

import java.io.IOException;
import java.io.Serializable;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.authservice.dto.UserDataDTO;
import br.com.authservice.dto.UserResponseDTO;
import br.com.reserveja.pessoa.dao.GenericDao;
import br.com.reserveja.pessoa.dao.PessoaDAOImpl;
import br.com.reserveja.pessoa.model.pessoa.Pessoa;
import br.com.reserveja.pessoa.service.AbstractService;

@Service
public class PessoaServiceImpl extends AbstractService<Pessoa>{

	final String uri = "http://localhost:8080/users/me";
	final String uriUpdateUser = "http://localhost:8080/updateUser";
	   	   
	@Autowired
	PessoaDAOImpl pessoaDAO;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public GenericDao<Pessoa, Serializable> getDao() {
		return pessoaDAO;
	}
	
	@Override
	public Pessoa insert(Pessoa t) {
		return pessoaDAO.cadastrarPessoa(t);
	}
	
	@Override
	public Pessoa list(Long id) {
		return super.list(id);
	}
	
	public UserResponseDTO obtemUsuarioLogado(String token) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authorization", token);
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<UserResponseDTO> result = restTemplate.exchange(uri, HttpMethod.GET, entity, UserResponseDTO.class);
	    
	    return result.getBody();
	    
	}
	
	public UserResponseDTO atualizaPessoaUser(UserDataDTO user, String token) {
				  try {
				String body = null;
				ObjectMapper mapper = new ObjectMapper();
				body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		        // Find room ID based on name
		       // String roomId = getRoomID(WEBSERVICE_URL, AUTHTOKEN, roomName);
				StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
		        CloseableHttpClient http = HttpClientBuilder.create().build();
		        HttpPatch updateRequest = new HttpPatch("http://localhost:8080/users/updateUser/"+user.getUsername());
		        updateRequest.setEntity(entity);
		        updateRequest.setHeader("Authorization", token);
		        

		        HttpResponse response = http.execute(updateRequest);
		        if (response.getStatusLine().getStatusCode() == 200) {
		        	return obtemUsuarioLogado(token);
		        } else {
		        	throw new IOException("Status nao esperado");
		        }
		        
		        
		    } catch (IOException e) {
		    	return null;
		    }
	}
	
}

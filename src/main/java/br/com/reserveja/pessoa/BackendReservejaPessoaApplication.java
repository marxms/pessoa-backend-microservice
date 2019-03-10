package br.com.reserveja.pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.reserveja.model.domain.endereco.Endereco;
import br.com.reserveja.model.domain.pessoa.Pessoa;
import br.com.reserveja.model.domain.telefone.Telefone;
import br.com.reserveja.model.domain.user.User;

@EnableAutoConfiguration
@EntityScan(basePackageClasses= {Pessoa.class, User.class, Endereco.class, Telefone.class})
@SpringBootApplication
public class BackendReservejaPessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendReservejaPessoaApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	@Qualifier(value = "entityManager")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
	    return entityManagerFactory.createEntityManager();
	}
}

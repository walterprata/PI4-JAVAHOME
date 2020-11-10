package br.com.javahome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class JavahomeApplication{
	public static void main(String[] args) {
		SpringApplication.run(JavahomeApplication.class, args);
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		//Expondo atributos
		resolver.setExposedContextBeanNames("carrinho","usuarioLogado");
		return resolver;
	}
}

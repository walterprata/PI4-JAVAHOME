package br.com.javahome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.javahome.model.pedido.PedidoStatus;
import br.com.javahome.repository.PedidoStatusRepository;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class JavahomeApplication{
	
	@Autowired
	private PedidoStatusRepository statusRepository;
	
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
	
	@Bean
	public void createStatus(){
		if(statusRepository.findAll().isEmpty()){
			statusRepository.save(new PedidoStatus(Constantes.STATUS_AGUARDANDO_PAGAMENTO));
			statusRepository.save(new PedidoStatus(Constantes.STATUS_REJEITADO));
			statusRepository.save(new PedidoStatus(Constantes.STATUS_PAGAMNETO_SUCESSO));
			statusRepository.save(new PedidoStatus(Constantes.STATUS_AGUARDANDO_RETIRADA));
			statusRepository.save(new PedidoStatus(Constantes.STATUS_EM_TRANSITO));
			statusRepository.save(new PedidoStatus(Constantes.STATUS_ENTREGUE));
		}
	}
}

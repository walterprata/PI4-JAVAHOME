package br.com.javahome.controller.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.javahome.model.produto.DuvidaProduto;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Duvidas implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<DuvidaProduto> duvidas = new ArrayList<>();
	
	public List<DuvidaProduto> getDuvidas() {
		return duvidas;
	}
	
	public void setDuvidas(List<DuvidaProduto> duvidas) {
		this.duvidas = duvidas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

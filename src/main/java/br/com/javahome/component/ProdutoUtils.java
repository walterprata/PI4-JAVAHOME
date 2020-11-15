package br.com.javahome.component;

import br.com.javahome.model.produto.Produto;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoUtils {
    public static void formataCaminhoDaImagenDosProdutos(List<Produto> produtos) {
        for(Produto p: produtos){
            @SuppressWarnings("unchecked")
			ArrayList<String> img = (ArrayList<String>) new Gson().fromJson(p.getCaminhoDaImagem(), ArrayList.class);
            if (img != null && !img.isEmpty()) {
                p.setCaminhoDaImagem(img.get(0));
            }
        }
    }

    public static void formataCaminhoDaImagenDosProdutos(Produto produto) {
        if(produto != null){
            @SuppressWarnings("unchecked")
			ArrayList<String> img = (ArrayList<String>) new Gson().fromJson(produto.getCaminhoDaImagem(), ArrayList.class);
            if (img != null && !img.isEmpty()) {
                produto.setCaminhoDaImagem(img.get(0));
            }
        }
    }
}

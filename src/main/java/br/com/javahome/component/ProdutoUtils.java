package br.com.javahome.component;

import br.com.javahome.model.Produto;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoUtils {
    public static void formataCaminhoDaImagenDosProdutos(List<Produto> produtos) {
        for(Produto p: produtos){
            ArrayList<String> img = (ArrayList<String>) new Gson().fromJson(p.getCaminhoDaImagem(), ArrayList.class);
            if (!img.isEmpty()) {
                p.setCaminhoDaImagem(img.get(0));
            }
        }
    }
}

package br.com.javahome.services;

import br.com.javahome.model.frete.Frete;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class EnviaCepServiceTeste {

    @Test
    public void retornaFreteMockado(){
        //Cenario
        EnviaCepService enviaCepService = new EnviaCepService();
        //Quando
        List<Frete> lista = enviaCepService.getEntregas();

        //Entao
        assertFalse(lista.isEmpty());
        assertEquals(4, lista.size());
    }


    @Test
    public void verificaSericosDeEntrega(){
        //Cenario
        EnviaCepService enviaCepService = new EnviaCepService();
        List<String> listaEsperada = Arrays.asList("SEDEX", "SEDEX 10", "JADLOG", "AZUL");
        //Quando
        List<Frete> listaServicos = enviaCepService.getEntregas();

        assertFalse(listaServicos.isEmpty());
        assertEquals(listaEsperada.get(0), listaServicos.get(0).getFreteNome());
        assertEquals(listaEsperada.get(1), listaServicos.get(1).getFreteNome());
        assertEquals(listaEsperada.get(2), listaServicos.get(2).getFreteNome());
        assertEquals(listaEsperada.get(3), listaServicos.get(3).getFreteNome());
    }
}

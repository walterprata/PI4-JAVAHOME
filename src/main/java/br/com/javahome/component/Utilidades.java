package br.com.javahome.component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utilidades {

    public String encryptaSenha(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }

    public Boolean validaSenha(String senha,String encryptSenha){
        return new BCryptPasswordEncoder().matches(senha,encryptSenha);
    }
}

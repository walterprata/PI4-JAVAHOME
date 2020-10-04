package br.com.javahome.component;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileSaver {
    @Autowired
    private ServletContext request;
    private final static String BASE_FOLDER = "files";

    public String write(MultipartFile[] file,Integer idUsuario) {
        if (file.length > 0 ) {
            List<String> files = new ArrayList<>();
            for (MultipartFile f : file) {
                if (!f.isEmpty()){
                    String realPath = request.getRealPath("WEB-INF/"+BASE_FOLDER);
                    try {
                        String path = realPath+"/"+idUsuario+f.getOriginalFilename();
                        f.transferTo(new File(path));
                        files.add("/"+idUsuario+f.getOriginalFilename());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return new Gson().toJson(files);
        }
        return "";
    }



}

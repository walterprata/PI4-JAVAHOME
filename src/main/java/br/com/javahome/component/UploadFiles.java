package br.com.javahome.component;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Component
public class UploadFiles {
    public static final String DIRECTORY = "WEB-INF/files/";

    public static String saveFiles(MultipartFile[] file, ServletContext servletContext) {
        if (file.length > 0) {
            List<String> files = new ArrayList<>();
            for (MultipartFile f : file) {
                try {
                    byte[] bytes = f.getBytes();
                    Path path = Paths.get(servletContext.getRealPath(DIRECTORY) + f.getOriginalFilename());
                    Files.write(path, bytes);
                    files.add(path.toString());
                    System.out.println(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new Gson().toJson(files);
        }
            return "";
    }
}
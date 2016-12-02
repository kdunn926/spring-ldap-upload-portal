package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new File(PortalController.uploadingdir).mkdirs();
        SpringApplication.run(Application.class, args);
    }

}

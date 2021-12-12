package de.dueto.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.util.Map;

@SpringBootApplication
public class MysqlApplication {

    public static void main(String[] args) {
        for(Map.Entry<String, String> entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        SpringApplication.run(MysqlApplication.class, args);
    }

}
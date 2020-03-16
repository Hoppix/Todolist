package de.uhh.hop.JavaCI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class JavaCiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(JavaCiApplication.class, args);
		 SpringApplication app = new SpringApplication(JavaCiApplication.class);
         app.setDefaultProperties(Collections
           .singletonMap("server.port", "8083"));
         app.run(args);
	}

}

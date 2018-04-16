package br.com.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Application {
	
	@RequestMapping("/")
	  public String home() {
	    return "Hello World";
	  }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

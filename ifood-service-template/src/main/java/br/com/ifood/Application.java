package br.com.ifood;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/books")
public class Application {
	
	private List<Book> bookList = Arrays.asList(
	        new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
	        new Book(2L, "Baeldung goes to the park", "Slavisa")
	    );

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("")
	public List<Book> findAllBooks() {
		return bookList;
	}

	@GetMapping("/{bookId}")
	public Book findBook(@PathVariable Long bookId) {
		return bookList.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
	}
	
	@Value("${lucky-word}") String luckyWord;
	  
	  @GetMapping("/lucky-word")
	  public String showLuckyWord() {
	    return "The lucky word is: " + luckyWord;
	  }

}

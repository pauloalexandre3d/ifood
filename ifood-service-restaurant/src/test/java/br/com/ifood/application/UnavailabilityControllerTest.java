package br.com.ifood.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifood.domain.Restaurant;
import br.com.ifood.domain.Unavailability;
import br.com.ifood.repository.Restaurants;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
public class UnavailabilityControllerTest {

	@Autowired
	private Restaurants restaurants;
	
	@LocalServerPort
	private int port;

	final String url = "/restaurants";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = url;

		restaurants.deleteAll();

		Restaurant restaurant = new Restaurant("Tanuki");
		restaurants.save(restaurant);
	}

	@Test
	public void testShoudAssertAScheduleOfUnavailability() {
		Unavailability unavailability = new Unavailability(LocalDateTime.now(), 2L, Unavailability.Reason.CONNECTION_ISSUES);
		ResponseEntity<Object> response = this.restTemplate.postForEntity(url.concat("/1/unavailability"), unavailability , Object.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		}

}

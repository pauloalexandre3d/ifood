package br.com.ifood.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifood.domain.Restaurant;

/**
 * store and delete schedules of unavailabilities test
 * @author paulo
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RestaurantsTest {

	@Autowired
	private Restaurants restaurants;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testName() throws Exception {
		Restaurant restaurant = new Restaurant();
		restaurant = restaurants.saveAndFlush(restaurant);
	}
	

	
}

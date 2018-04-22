package br.com.ifood.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.ifood.domain.Unavailability;

public interface Availabilities extends JpaRepository<Unavailability, Long> {

	@Modifying
	@Query("delete from Unavailability u where u.restaurant.id = ?1 and start =?2")
	  void deleteByIdRestaurantAndStartDate(long idRestaurant, LocalDateTime start);
}

package br.com.ifood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifood.domain.Restaurant;

public interface Restaurants extends JpaRepository<Restaurant, Long> {

}

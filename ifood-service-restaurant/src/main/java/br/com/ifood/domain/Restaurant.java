package br.com.ifood.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESTAURANT_ID")
	private Long id;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Unavailability> unavailabilitySchedule;
	private String name;
	@Transient
	private LocalDateTime now;

	public Restaurant(LocalDateTime now) {
		this.now = now;
		this.unavailabilitySchedule = new ArrayList<>();
	}

	public Restaurant() {
	}

	public Restaurant(String name) {
		this.name = name;
		this.unavailabilitySchedule = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Unavailability> getUnavailabilitySchedule() {
		return unavailabilitySchedule;
	}

	public void addScheduled(Unavailability unavailability) {
		unavailability.setRestaurant(this);
		this.unavailabilitySchedule.add(unavailability);

	}

	public boolean isAvailable() {
		if (this.now == null) {
			this.now = LocalDateTime.now();
		}

		if (this.unavailabilitySchedule.isEmpty()) {
			return true;
		} else {
			Predicate<? super Unavailability> isUnavailable = u -> (this.now.isAfter(u.getStart())
					&& this.now.isBefore(u.getEnd()));
			Optional<Unavailability> findAny = unavailabilitySchedule.stream().filter(isUnavailable).findAny();
			return !findAny.isPresent();
		}
	}

}

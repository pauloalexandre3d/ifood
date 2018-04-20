package br.com.ifood.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Restaurant {

	private List<Unavailability> unavailabilitySchedule;
	private LocalDateTime now;

	public Restaurant(LocalDateTime now) {
		this.now = now;
		this.unavailabilitySchedule = new ArrayList<>();
	}

	public Restaurant() {
	}

	public void addScheduled(Unavailability unavailability) {
		this.unavailabilitySchedule.add(unavailability);

	}

	public boolean isAvailable() {
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

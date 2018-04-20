package br.com.ifood.domain;

import java.time.LocalDateTime;

public class Unavailability {
	
	enum Reason {
		LACK_OF_DELIVERY_STAFF("lack of delivery staff"),
		CONNECTION_ISSUES("connection issues"),
		OVERLOADED_DUE_TO_OFFLINE_ORDERS("overloaded due to offline orders"),
		HOLIDAYS("holidays");
	
		String value;
		Reason(String value){
			this.value = value;
		}
		
	}

	private LocalDateTime start;
	private LocalDateTime end;
	private Reason reason;

	public Unavailability(LocalDateTime when, int unavailableTime, Reason reason) {
		this.start = when;
		this.end = this.start.plusMinutes(unavailableTime);
		this.reason = reason;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	public Reason getReason() {
		return reason;
	}

}

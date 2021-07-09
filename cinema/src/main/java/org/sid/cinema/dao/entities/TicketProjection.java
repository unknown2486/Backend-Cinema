package org.sid.cinema.dao.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketProj",types=Ticket.class)
public interface TicketProjection {
	public Long getId();
	public String GetNomClient();
	public double getPrix();
	public Integer codePayement();
	public boolean getReserve();
	public Place getPlace();
	
	
}

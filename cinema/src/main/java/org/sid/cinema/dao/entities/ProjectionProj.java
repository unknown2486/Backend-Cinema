package org.sid.cinema.dao.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1",types= {org.sid.cinema.dao.entities.Projection.class})

public interface ProjectionProj {
	public Long getId();
	public double getPrix();
	public Date getDateProjetion();
	public Salle getSalle();
	public Film getFilm();
	public Seance getSeance();
	public Collection<Ticket> getTickets();
	

}

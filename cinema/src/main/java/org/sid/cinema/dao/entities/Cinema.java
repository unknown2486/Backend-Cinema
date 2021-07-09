package org.sid.cinema.dao.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	private String name;
	private double longitude,latitude,altitude;
	private int nombresSalles;
	@OneToMany(mappedBy="cinema")
	private Collection<Salle> salles;
	@ManyToOne
	private Ville ville;
	
	
	
	

}

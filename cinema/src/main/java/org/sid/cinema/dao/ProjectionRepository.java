package org.sid.cinema.dao;

import org.sid.cinema.dao.entities.Cinema;
import org.sid.cinema.dao.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")

public interface ProjectionRepository extends  JpaRepository<Projection,Long>{
	

}

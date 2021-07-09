package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema.dao.CategorieRepository;
import org.sid.cinema.dao.CinemaRepository;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.PlaceRepository;
import org.sid.cinema.dao.ProjectionRepository;
import org.sid.cinema.dao.SalleRepository;
import org.sid.cinema.dao.SeanceRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.dao.VilleRepository;
import org.sid.cinema.dao.entities.Categorie;
import org.sid.cinema.dao.entities.Cinema;
import org.sid.cinema.dao.entities.Film;
import org.sid.cinema.dao.entities.Place;
import org.sid.cinema.dao.entities.Projection;
import org.sid.cinema.dao.entities.Salle;
import org.sid.cinema.dao.entities.Seance;
import org.sid.cinema.dao.entities.Ticket;
import org.sid.cinema.dao.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
	private VilleRepository villeRepository;
    @Autowired
    private  CinemaRepository cinemaRepository;// pour enregistrer les cinemas
    @Autowired
    private  SalleRepository salleRepository;
    @Autowired
    private  PlaceRepository placeRepository;
    @Autowired
    private  SeanceRepository seanceRepository;
    @Autowired
    private  FilmRepository filmRepository;
    @Autowired
    private  ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private TicketRepository  ticketRepository;
    
	
	@Override
	public void initVilles() {
			Stream.of("casablanca","Marrakech","Rabat","Tanger").forEach(nameville->{
				 Ville ville = new Ville();
				 ville.setName(nameville);
				villeRepository.save(ville);
			});
			
			
		
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v->{
			Stream.of("MegaRama","IMax","FOUNOUN","CHAHRAZAD","DAOULIZ").forEach(nameCinema->{
				Cinema cinema = new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombresSalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			});
		});
		
		
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
		 for(int i=0;i<cinema.getNombresSalles();i++) {
			 Salle salle=new Salle();
			 salle.setName("Salle "+(i+1));
			 salle.setCinema(cinema);
			 salle.setNombrePlace(15+(int)(Math.random()*20));
			 salleRepository.save(salle);
		 }
				
			});
		
	}

	@Override
	public void initPlaces() {
		 salleRepository.findAll().forEach(salle-> {
			 for( int i=0;i<salle.getNombrePlace();i++) {
				 Place place= new Place();
				 place.setNumero(i+1);
				 place.setSalle(salle);
				 placeRepository.save(place);
			 }
		 });
		
	}

	@Override
	public void initSeances() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		java.util.stream.Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
			Seance seance=new Seance();
			try {
				seance.setHeureDebut(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void initCategories() {
		java.util.stream.Stream.of("Histoire","Actions","Fiction","Drama").forEach(cat->{
			Categorie categorie=new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});
		
	}

	@Override
	public void initfilms() {
		double[] duree=new double[] {1,1.5,2,2.5,3};
		List<Categorie> categories=categorieRepository.findAll();
		java.util.stream.Stream.of("it","Venom","Aquaman","Avatar","Joker"
				,"Avengers").forEach(titreFilm->{
					Film film=new Film();
					film.setTitre(titreFilm);
					film.setDuree(duree[new Random().nextInt(duree.length)]);
					film.setPhoto(titreFilm.replaceAll(" ", "")+".jpg");
					film.setCategorie(categories.get(new Random().nextInt(categories.size())));
					filmRepository.save(film);
				});;
		
	}

	@Override
	public void initProjections() {
		double[] prices=new double[] {30,50,60,70,90,100};
		List<Film> films=filmRepository.findAll();
		   villeRepository.findAll().forEach(ville->{
			   ville.getCinemas().forEach(cinema->{
				   cinema.getSalles().forEach(salle->{
					   int index=new Random().nextInt(films.size());
					  Film film=films.get(index);
						  seanceRepository.findAll().forEach(seance->{
							  Projection projection=new Projection();
							  projection.setDateProjetion(new Date());
							  projection.setFilm(film);
							  projection.setPrix(prices[new Random().nextInt(prices.length)]);
							  projection.setSalle(salle);
							  projection.setSeance(seance);
							  projectionRepository.save(projection);
						  }) ;
					  
				   });
			   });
		   });
		
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlace().forEach(place->{
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReserve(false);
				ticketRepository.save(ticket); 
			});
		});
		
	}

}

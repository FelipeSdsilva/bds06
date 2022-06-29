package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService authService;

	public ReviewDTO insert(ReviewDTO dto) {

		User user = authService.authenticated();

		Review entity = new Review();
		entity.setText(dto.getText());
		entity.setUser(user);

		Movie movie = new Movie();
		movie.setId(dto.getMovieId());

		entity.setMovie(movie);
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> find(Long movieId) {
		User user = authService.authenticated();
		
		Optional<Movie> obj = movieRepository.findById(movieId);
		@SuppressWarnings("unused")
		Movie movie = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		List<Review> list = repository.find(user,movieId);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
}

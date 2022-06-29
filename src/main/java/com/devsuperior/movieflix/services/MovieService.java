package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;


@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public Page<MovieGenreDTO> findAll(Long genreId, Pageable pageable) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page = repository.search(genre, pageable);
		return page.map(x -> new MovieGenreDTO(x));
	}

	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new MovieDTO(entity);
	}

	public List<ReviewDTO> findReview(Long movieId) {
		Optional<Movie> obj = repository.findById(movieId);
		@SuppressWarnings("unused")
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		List<Review> list = reviewRepository.findAll();
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}

}

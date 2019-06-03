package com.gmail.arthurstrokov.ormedia.repository;

import com.gmail.arthurstrokov.ormedia.model.FilmRating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<FilmRating, Long> {

        FilmRating findByFilmIdAndUserId(Long filmId, Long userId);
}
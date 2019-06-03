package com.gmail.arthurstrokov.ormedia.repository;

import com.gmail.arthurstrokov.ormedia.model.FilmRating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<FilmRating, Long> {

        FilmRating findByFilm_IdAndUser_Id(Long filmId, Long userId);
}
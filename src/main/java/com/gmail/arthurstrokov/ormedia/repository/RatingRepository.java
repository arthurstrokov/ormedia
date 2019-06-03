package com.gmail.arthurstrokov.ormedia.repository;

import com.gmail.arthurstrokov.ormedia.model.FilmRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<FilmRating, Long> {

    FilmRating findByFilmIdAndUserId(Long filmId, Long userId);
}
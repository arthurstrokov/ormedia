package com.gmail.arthurstrokov.ormedia.service;

import com.gmail.arthurstrokov.ormedia.model.FilmRating;
import com.gmail.arthurstrokov.ormedia.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public void save(FilmRating filmRating) {
        ratingRepository.save(filmRating);
    }


    public FilmRating findByFilmIdAndUserId(Long filmId, Long userId) {
        return ratingRepository.findByFilm_IdAndUser_Id(filmId, userId);
    }
}
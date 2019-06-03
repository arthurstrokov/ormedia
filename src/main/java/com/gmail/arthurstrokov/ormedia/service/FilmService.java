package com.gmail.arthurstrokov.ormedia.service;

import com.gmail.arthurstrokov.ormedia.model.FilmRating;
import com.gmail.arthurstrokov.ormedia.repository.FilmRepository;
import com.gmail.arthurstrokov.ormedia.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    RatingRepository ratingRepository;

    public void save(FilmRating filmRating) {
        ratingRepository.save(filmRating);
    }
}

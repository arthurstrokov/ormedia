package com.gmail.arthurstrokov.ormedia.service;

import com.gmail.arthurstrokov.ormedia.model.Film;
import com.gmail.arthurstrokov.ormedia.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Film findById(Long id) {
        return filmRepository.findFilmById(id);
    }
}
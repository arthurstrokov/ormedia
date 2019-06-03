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

    public Iterable<Film> findByTitle(String filter) {
        return filmRepository.findByTitle(filter);
    }

    public Iterable<Film> findAll() {
        return filmRepository.findAll();
    }

    public void save(Film film) {
        filmRepository.save(film);
    }

    public Film findFilmById(Long filmId) {
        return filmRepository.findFilmById(filmId);
    }
}
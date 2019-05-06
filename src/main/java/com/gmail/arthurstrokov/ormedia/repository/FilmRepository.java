package com.gmail.arthurstrokov.ormedia.repository;

import com.gmail.arthurstrokov.ormedia.model.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long> {

    List<Film> findByTitle(String title);
}
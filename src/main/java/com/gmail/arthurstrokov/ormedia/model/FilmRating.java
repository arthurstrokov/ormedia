package com.gmail.arthurstrokov.ormedia.model;

import javax.persistence.*;

@Entity
public class FilmRating {

    @EmbeddedId
    private FilmRatingKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("film_id")
    @JoinColumn(name = "film_id")
    private Film film;

    private Long rating;

    public FilmRating() {
    }

    public FilmRating(FilmRatingKey id, User user, Film film, Long rating) {
        this.id = id;
        this.user = user;
        this.film = film;
        this.rating = rating;
    }

    public FilmRatingKey getId() {
        return id;
    }

    public void setId(FilmRatingKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "FilmRating{" +
                "rating=" + rating +
                '}';
    }
}
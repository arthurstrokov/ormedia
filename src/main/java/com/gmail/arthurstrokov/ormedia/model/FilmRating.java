package com.gmail.arthurstrokov.ormedia.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_rating")
public class FilmRating {

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "film_id")
    Film film;

    @Id
    @GeneratedValue
    private Long filmRatingKeyId;

    private Long rating;

    public FilmRating() {
    }

    public FilmRating(Long filmRatingKeyId, User user, Film film, Long rating) {
        this.filmRatingKeyId = filmRatingKeyId;
        this.user = user;
        this.film = film;
        this.rating = rating;
    }

    public Long getFilmRatingKeyId() {
        return filmRatingKeyId;
    }

    public void setFilmRatingKeyId(Long filmRatingKeyId) {
        this.filmRatingKeyId = filmRatingKeyId;
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
                "filmRatingKeyId=" + filmRatingKeyId +
                ", user=" + user +
                ", film=" + film +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmRating that = (FilmRating) o;
        return Objects.equals(filmRatingKeyId, that.filmRatingKeyId) &&
                Objects.equals(user, that.user) &&
                Objects.equals(film, that.film) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmRatingKeyId, user, film, rating);
    }
}
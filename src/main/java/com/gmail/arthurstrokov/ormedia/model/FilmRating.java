package com.gmail.arthurstrokov.ormedia.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_rating")
public class FilmRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long filmRatingKeyId;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @MapsId("user_id")
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id", nullable = false, insertable = false, updatable = false)
    @MapsId("film_id")
    private Film film;

    private Long rating;

    public FilmRating() {
    }

    public FilmRating(Long filmRatingKeyId, User user, Film film, Long rating) {
        this.filmRatingKeyId = filmRatingKeyId;
        this.user = user;
        this.film = film;
        this.rating = rating;
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
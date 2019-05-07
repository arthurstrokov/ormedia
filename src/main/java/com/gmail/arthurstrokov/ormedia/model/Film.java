package com.gmail.arthurstrokov.ormedia.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "text")
    @NotBlank(message = "Please fill the title!")
    @Length(max = 2147483647, message = "Title too long!")
    private String title;

    @Length(max = 2147483647, message = "Genre too long!")
    private String genre;

    private String year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    @OneToMany(mappedBy = "film")
    Set<FilmRating> filmRatings;

    public Film() {
    }

    public Film(String title, String genre, String year, User user) {
        this.author = user;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Set<FilmRating> getFilmRatings() {
        return filmRatings;
    }

    public void setFilmRatings(Set<FilmRating> filmRatings) {
        this.filmRatings = filmRatings;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                ", author=" + author +
                ", filename='" + filename + '\'' +
                ", filmRatings=" + filmRatings +
                '}';
    }
}
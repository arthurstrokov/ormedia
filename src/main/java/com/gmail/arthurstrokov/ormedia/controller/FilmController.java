package com.gmail.arthurstrokov.ormedia.controller;

import com.gmail.arthurstrokov.ormedia.model.Film;
import com.gmail.arthurstrokov.ormedia.model.FilmRating;
import com.gmail.arthurstrokov.ormedia.model.User;
import com.gmail.arthurstrokov.ormedia.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String films(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Film> films;

        if (filter != null && !filter.isEmpty()) {
            films = filmRepository.findByTitle(filter);
        } else {
            films = filmRepository.findAll();
        }

        model.addAttribute("films", films);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String addFilm(
            @AuthenticationPrincipal User user,
            @Valid Film film,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        film.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("film", film);
            //TODO is it be the same page for success and error?
        } else {
            uploadImage(film, file);

            model.addAttribute("film", null);

            filmRepository.save(film);
        }

        Iterable<Film> films = filmRepository.findAll();

        model.addAttribute("films", films);
        // TODO what else attributes? filter, success message maybe?
        return "main";
    }

    private void uploadImage(@Valid Film film, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            film.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-films/{user}")
    public String userFilms(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            //XXX what is the purpose of film here?
            @RequestParam(required = false) Film film
    ) {
        Set<Film> films = user.getFilms();

        model.addAttribute("films", films);
        model.addAttribute("film", film);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userFilms";
    }

    @PostMapping("/user-films/{user}")
    public String updateFilm(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Film film,
            @RequestParam("title") String title,
            @RequestParam("genre") String genre,
            @RequestParam("year") String year,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (film.getAuthor().equals(currentUser)) {

            if (!StringUtils.isEmpty(title)) {
                film.setTitle(title);
            }

            if (!StringUtils.isEmpty(genre)) {
                film.setGenre(genre);
            }

            if (!StringUtils.isEmpty(year)) {
                film.setYear(year);
            }

            uploadImage(film, file);

            filmRepository.save(film);
        }
        //TODO what is else branch?  How to show user success or failure?

        return "redirect:/user-films/" + user;
    }

    @PostMapping(value = "/user-films/{user}/{film}/rate", produces = "application/json")
    public String rateFilm(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("film_id") Film film,
            @RequestParam("your_mark") Long your_mark
    ) {

        FilmRating filmRating = new FilmRating();

        filmRating.setUser(currentUser);
        filmRating.setFilm(film);
        filmRating.setRating(your_mark);

        film.setFilmRatings(Collections.singleton(filmRating));

        filmRepository.save(film);
        return "redirect:/user-films/" + user;
    }
}
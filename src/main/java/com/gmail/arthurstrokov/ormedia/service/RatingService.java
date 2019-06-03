package com.gmail.arthurstrokov.ormedia.service;

import com.gmail.arthurstrokov.ormedia.model.FilmRating;
import com.gmail.arthurstrokov.ormedia.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class RatingService {

    private static Logger logger = Logger.getAnonymousLogger();

    @Autowired
    private RatingRepository ratingRepository;

    public void save(FilmRating filmRating) {
        ratingRepository.saveAndFlush(filmRating);
    }

    public FilmRating findByFilmIdAndUserId(Long filmId, Long userId) {
        return ratingRepository.findByFilmIdAndUserId(filmId, userId);
    }

    public static List<String> getParameters(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        logger.info(data);

        List<String> parameters = new ArrayList<>();
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pat.matcher(data);
        while (matcher.find()) {
            parameters.add(matcher.group());
        }
        logger.info(String.valueOf(parameters));
        return parameters;
    }
}
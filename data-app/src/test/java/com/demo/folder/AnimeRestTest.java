package com.demo.folder;

import com.demo.folder.dto.Anime;
import com.demo.folder.service.AnimeRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AnimeRestTest {
    AnimeRestClient moviesRestClient = null;
    WebClient webClient = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimeRestTest.class);

    @BeforeEach
    void setUp() {
        int port = 8080;
        final String baseUrl = String.format("http://localhost:%s/", port);
        webClient = WebClient.create(baseUrl);
        moviesRestClient = new AnimeRestClient(webClient);
    }

    @Test
    void getAllAnime() {
        //when
        List<Anime> movieList = moviesRestClient.getAllAnime();
        LOGGER.info("\nAnime list: {}", movieList);
        //then
        assertFalse(movieList.isEmpty());
    }
}

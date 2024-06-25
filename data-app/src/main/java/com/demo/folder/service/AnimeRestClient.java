package com.demo.folder.service;

import com.demo.folder.dto.Anime;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
public class AnimeRestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimeRestClient.class);

    private WebClient webClient;

    public AnimeRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Anime> getAllAnime() {

    }

    public Anime getAnimeById(Integer id) {

    }

    public Anime  postNewAnime(Anime anime) {

    }

    public Anime updateExistingAnime(Integer animeId, Anime anime) {

    }

    public String deleteAnimeById(Integer animeId) {

    }
}

package com.demo.folder.service;

import com.demo.folder.dto.Anime;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.demo.folder.endpoints.UtilEndpoints.GET_ALL_ANIME;

public class AnimeRestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimeRestClient.class);

    private WebClient webClient;

    public AnimeRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Anime> getAllAnime() {
        List<Anime> animeList = List.of();
        try {
            animeList = webClient.get().uri(GET_ALL_ANIME).retrieve().bodyToFlux(Anime.class).collectList().block();
        } catch (WebClientResponseException e) {
            LOGGER.error("WebClientResponseException: {}", e.getResponseBodyAsString());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
        }
        return animeList;
    }

    public Anime getAnimeById(Integer id) {


        return null;
    }

    public Anime  postNewAnime(Anime anime) {


        return null;
    }

    public Anime updateExistingAnime(Integer animeId, Anime anime) {

        return null;
    }

    public String deleteAnimeById(Integer animeId) {

        return null;
    }
}

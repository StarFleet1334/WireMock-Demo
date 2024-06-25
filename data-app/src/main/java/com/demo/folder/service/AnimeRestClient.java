package com.demo.folder.service;

import com.demo.folder.dto.Anime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.demo.folder.endpoints.UtilEndpoints.*;

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
        Anime anime = null;
        try {
            anime = webClient.get().uri(GET_ANIME_BY_ID, id)
                    .retrieve()
                    .bodyToMono(Anime.class)
                    .block();
        } catch (WebClientResponseException ex) {
            LOGGER.error("WebClientResponseException - Error Message is : {} ", ex.getResponseBodyAsString());
        } catch (Exception ex) {
            LOGGER.error("Exception - The Error Message is {} ", ex.getMessage());
        }
        return anime;
    }

    public Anime postNewAnime(Anime newAnime) {
        Anime anime = null;
        try {
            anime = webClient.post().uri(POST_ANIME_BY_ID)
                    .syncBody(newAnime)
                    .retrieve()
                    .bodyToMono(Anime.class)
                    .block();
            LOGGER.info("New Movie SuccessFully addded {} ", anime);
        } catch (WebClientResponseException ex) {
            LOGGER.error("WebClientResponseException - Error Message is : {} , and the Error Response Body is {}", ex, ex.getResponseBodyAsString());
        } catch (Exception ex) {
            LOGGER.error("Exception - The Error Message is {} ", ex.getMessage());
        }
        return anime;
    }

    public Anime updateExistingAnime(Integer animeId, Anime anime) {
        Anime updatedAnime = null;

        try {
            updatedAnime = webClient.put().uri(PUT_ANIME_BY_ID, animeId)
                    .syncBody(anime)
                    .retrieve()
                    .bodyToMono(Anime.class)
                    .block();
            LOGGER.info(" Movie SuccessFully updated {} ", updatedAnime);
        } catch (WebClientResponseException ex) {
            LOGGER.error("WebClientResponseException - Error Message is : {}", ex.getResponseBodyAsString());
        } catch (Exception ex) {
            LOGGER.error("Exception - The Error Message is {} ", ex.getMessage());
        }

        return updatedAnime;
    }

    public String deleteAnimeById(Integer animeId) {
        String response = "";
        try {
            response = webClient.delete().uri( DELETE_ANIME_BY_ID, animeId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (WebClientResponseException ex) {
            LOGGER.error("WebClientResponseException - Error Message is : {}",ex.getResponseBodyAsString());
        } catch (Exception ex) {
            LOGGER.error("Exception - The Error Message is {} ", ex.getMessage());
        }
        return response;
    }
}

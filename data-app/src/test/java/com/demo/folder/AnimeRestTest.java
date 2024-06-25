package com.demo.folder;

import com.demo.folder.dto.Anime;
import com.demo.folder.service.AnimeRestClient;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void retrieveAnimeById() {
        //given
        Integer animeId = 2;

        //when
        Anime anime = moviesRestClient.getAnimeById(animeId);

        LOGGER.info("Anime retrieved: {}", anime);
        //then
        assertEquals("Naruto", anime.getTitle());
    }


    @Test
    void addNewAnime() {
        //given
        Anime newAnime =  new Anime();
        newAnime.setTitle("Kavawaki");
        newAnime.setRating(9.2);
        newAnime.setMaincharacter("Bob");
        newAnime.setDescription("It is long story");

        //when
        Anime anime = moviesRestClient.postNewAnime(newAnime);

        //then
        assertTrue(anime.getTitle() != null);

    }



    @Test
    void updateMovie() {
        //given
        Anime updatedAnime = new Anime(3, "One Piece (Found)", 9, "Monkey D. Luffy", "Luffy and his pirate crew in search of the legendary treasure called \"One Piece\" to become the Pirate King.");

        Integer animeId = 3;

        //when
        Anime anime = moviesRestClient.updateExistingAnime(animeId, updatedAnime);

        //then
        String newTitle = "One Piece (Found)";
        assertTrue(anime.getTitle().contains(newTitle));
    }


    @Test
    void deleteMovie() {

        //given
        String batmanBeginsCrew = "Tom Hanks, Tim Allen";
        Anime anime = new Anime(4, "My Hero Academia", 8.8, "Izuku Midoriya", "A boy born without superpowers in a world where they are common, but still dreams of becoming a superhero.");


        //when
        String response = moviesRestClient.deleteAnimeById(4);
        LOGGER.info("\nAnime response: {}", response);
    }




}

package com.demo.folder.controller;

import com.demo.folder.dto.Anime;
import com.demo.folder.repository.AnimeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimeController {
    @Autowired
    private AnimeRepository repository;

    public AnimeController(AnimeRepository repository) {
        this.repository = repository;
    }

    @Tag(name = "get", description = "Retrieve all animes")
    @GetMapping("/animes")
    public List<Anime> findAllAnimes() {
        return repository.findAll();
    }

    @Tag(name = "get", description = "Retrieve one anime")
    @GetMapping("/animes/{animeId}")
    public Anime getAnime(@Parameter(description = "ID of the anime to be retrieved", required = true)
                          @PathVariable int animeId) {
        return repository.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime id not found - " + animeId));
    }

    @PostMapping("/animes")
    public Anime addAnime(@RequestBody Anime anime) {
        anime.setId(0); // Reset ID for generated value
        return repository.save(anime);
    }

    @Operation(summary = "Update an anime",
            description = "Update an existing anime. The response is updated Anime object with id, title, rating, main character, and description.")
    @PutMapping("/animes")
    public Anime updateAnime(@RequestBody Anime anime) {
        return repository.save(anime);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Anime.class))}),
            @ApiResponse(responseCode = "404", description = "Anime not found",
                    content = @Content)})
    @DeleteMapping("/animes/{animeId}")
    public String deleteAnime(@PathVariable int animeId) {
        Anime anime = repository.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime id not found - " + animeId));
        repository.delete(anime);
        return "Deleted anime with id: " + animeId;
    }

}
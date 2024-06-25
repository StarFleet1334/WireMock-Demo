package com.demo.folder.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anime {

    @Id
    @NotNull
    private int id;
    @NotNull
    private String title;
    @NotNull
    private double rating;
    @NotNull
    private String maincharacter;
    @NotNull
    private String description;

    public Anime(String title, double rating, String maincharacter, String description) {
        this.title = title;
        this.rating = rating;
        this.maincharacter = maincharacter;
        this.description = description;
    }
}

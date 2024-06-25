package com.demo.folder.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anime {

    private int id;
    private String title;
    private double rating;
    private String maincharacter;
    private String description;

}

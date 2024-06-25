package com.demo.folder.repository;

import com.demo.folder.dto.Anime;
import org.springframework.data.repository.ListCrudRepository;

public interface AnimeRepository extends ListCrudRepository<Anime,Integer> {
}

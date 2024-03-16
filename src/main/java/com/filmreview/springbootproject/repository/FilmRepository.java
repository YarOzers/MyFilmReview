package com.filmreview.springbootproject.repository;

import com.filmreview.springbootproject.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
}

package com.filmreview.springbootproject.service;

import com.filmreview.springbootproject.exception.ResourceAlreadyExistsException;
import com.filmreview.springbootproject.exception.ResourceNotFoundException;
import com.filmreview.springbootproject.model.Film;
import com.filmreview.springbootproject.repository.FilmRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Film doesn't exist by id: " + id)
                );
    }

    public List<Film> findAll(int pageNumber, int rowPerPage) {
        List<Film> films = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber-1, rowPerPage,
                Sort.by("id").ascending());
        filmRepository.findAll(sortedByIdAsc).forEach(films::add);
        return films;
    }

    public Film save(Film film) {
        if (film.getId() != null && existsById(film.getId())) {
            throw new ResourceAlreadyExistsException("Film with id: " + film.getId() + " already exists!");
        }
        return filmRepository.save(film);
    }

    public void update(Film film) {
        if (!existsById(film.getId())) {
            throw new ResourceNotFoundException("Cannot find Film with id: " + film.getId());
        }
        filmRepository.save(film);
    }

    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find Film with id: " + id);
        } else {
            filmRepository.deleteById(id);
        }
    }

    private boolean existsById(Long id) {
        return filmRepository.existsById(id);
    }

    public long count() {
        return filmRepository.count();
    }


}

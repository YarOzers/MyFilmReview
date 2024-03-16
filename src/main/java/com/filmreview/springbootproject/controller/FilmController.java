package com.filmreview.springbootproject.controller;

import com.filmreview.springbootproject.exception.ResourceAlreadyExistsException;
import com.filmreview.springbootproject.exception.ResourceNotFoundException;
import com.filmreview.springbootproject.model.Film;
import com.filmreview.springbootproject.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@Controller
public class FilmController {
    private final int ROW_PER_PAGE = 3;
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/home")
    public String findAll(Model model,
                          @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Film> films = filmService.findAll(pageNumber, ROW_PER_PAGE);
        long count = filmService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("films", films);
        model.addAttribute("hasPrev",hasPrev);
        model.addAttribute("prev",pageNumber-1);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("next",pageNumber+1);
        return "home";
    }

    @GetMapping("/home/add-film")
    public String showAddFilm(Model model) {
        Film film = new Film();
        model.addAttribute("film",film);
        log.info("Attribute added");
        return "add_film";
    }

    @PostMapping("/home/add-film")
    public String addFilm(Model model,
                          @ModelAttribute("film") @Valid Film film,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_film";
        }
        try {
            filmService.save(film);
            return "redirect:/home";
        } catch (ResourceAlreadyExistsException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage",errorMessage);
            log.error(e.getMessage(),"saving movie was not succeed");
            return "add_film";
        }
    }

    @GetMapping("/home/edit/{filmId}")
    public String showUpdateForm(Model model,
                               @PathVariable long filmId) {

        Film film = null;
        try{
            film = filmService.findById(filmId);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("errorMessage","Film not found");
            log.error(e.getMessage(),"film not found");
        }
        model.addAttribute("film",film);
        return "film_edit";
    }

    @PostMapping("/home/update/{filmId}")
    public String updateFilm(Model model,
                           @PathVariable long filmId,
                           @ModelAttribute("film") @Valid Film film,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "film_edit";
        }
        try{
            film.setId(filmId);
            filmService.update(film);
            return "redirect:/home/films/" + film.getId();

        } catch (ResourceNotFoundException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage",errorMessage);
            log.error(e.getMessage(),"film was not found");


            return "film_edit";
        }
    }

    @GetMapping("/home/films/{filmId}")
    public String getFilmById(Model model,
                              @PathVariable long filmId) {

        model.addAttribute("film",filmService.findById(filmId));

        return "film";
    }

    @GetMapping("/home/delete/{filmId}")
    public String deleteOne(@PathVariable Long filmId,
                            Model model) {

        filmService.deleteById(filmId);

        return "redirect:/home";
    }
}

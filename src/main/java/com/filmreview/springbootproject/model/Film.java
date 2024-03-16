package com.filmreview.springbootproject.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tittle may not be blank.")
    private String title;

    @Lob
    @NotBlank(message = "Description may not be blank.")
    @Length(min = 20, message = "Min 20!!!")
    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Lob
    @NotBlank(message = "Type url, which reference on the image")
    private String url_image;

    public Film() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}

package com.epam.jdi.bookstore.model;

import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "book")
public class Book extends DataClass<Book> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long id;

    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN must consist of 10 or 13 digits with or without hyphens. Spaces are not allowed.")
    @Column(name = "isbn", nullable = false)
    public String isbn;

    @NotBlank(message = "Title is mandatory")
    @Column(name = "title", nullable = false)
    public String title;

    @NotBlank(message = "Author is mandatory")
    @Column(name = "author", nullable = false)
    public String author;

    @NotBlank(message = "Publication year is mandatory")
    @Column(name = "publication_year", nullable = false)
    public String publicationYear;

    @NotBlank(message = "Price is mandatory")
    @Column(name = "price", nullable = false)
    public String price;

    @NotNull(message = "Quantity is mandatory")
    @Column(name = "quantity", nullable = false)
    public Integer quantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(
                    name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "genre_id", referencedColumnName = "id"))
    public List<Genre> genres;
}

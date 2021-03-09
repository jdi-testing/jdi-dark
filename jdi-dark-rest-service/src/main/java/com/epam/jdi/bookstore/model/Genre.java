package com.epam.jdi.bookstore.model;

import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre extends DataClass<Genre> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long id;

    @NotBlank(message = "Genre type is mandatory")
    @Column(name = "type", nullable = false)
    public String type;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    transient public List<Book> books;
}

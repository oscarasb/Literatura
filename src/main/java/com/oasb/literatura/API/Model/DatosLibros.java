package com.oasb.literatura.API.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class DatosLibros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("download_count")
    private Double numeroDeDescargas;

    @ElementCollection (fetch = FetchType.EAGER)
    @JsonAlias("languages")
    private List<String> lenguajes;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<DatosAutor> autor;

   @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<DatosAutor> autores = new ArrayList<>();
   // @ManyToOne
   // @JoinColumn(name = "autor_id")
   // private DatosAutor autors;

    // 👉 Constructor vacío para JPA
    public DatosLibros() {}
    @JsonAlias("authors")
    @Transient // ⚠️ opcional, si no deseas persistir este campo
    private List<AutorDTO> authors;

    public List<AutorDTO> getAuthors() {
        return authors;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setAutores(List<DatosAutor> autores) {
        this.autores = autores;
    }
    public List<DatosAutor> getAutores() {
        return autores;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
}

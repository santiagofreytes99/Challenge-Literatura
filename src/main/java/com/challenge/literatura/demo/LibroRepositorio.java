package com.challenge.literatura.demo;
import com.challenge.literatura.demo.Idiomas;
import com.challenge.literatura.demo.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libros,Long> {
    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(:nombre)")
    Optional<Libros> obtenerLibroPorNombre(String nombre);

    @Query("SELECT l FROM Libro l WHERE l.idioma=:idioma")
    List<Libros> obtenerLibrosPorIdioma(Idiomas idioma);
}
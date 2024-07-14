package com.challenge.literatura.demo;
import com.challenge.literatura.demo.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

    @Repository
    public interface AutorRepositorio extends JpaRepository<Autores, Long> {
        @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(:nombre)")
        Optional<Autores> obtenerAutorPorNombre(String nombre);

        @Query("SELECT a FROM Autor a WHERE :anio>=a.fechaNacimiento AND :anio<a.fechaFallecimiento")
        List<Autores> obtenerAutoresVivosEnAnio(int anio);
    }
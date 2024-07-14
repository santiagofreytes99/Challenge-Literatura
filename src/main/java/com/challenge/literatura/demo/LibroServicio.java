package com.challenge.literatura.demo;

import com.challenge.literatura.demo.Idiomas;
import com.challenge.literatura.demo.Libros;
import com.challenge.literatura.demo.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LibroServicio {

    private LibroRepositorio libroRepository;

    @Autowired
    public LibroServicio(LibroRepositorio repository) {
        this.libroRepository = repository;
    }

    public LibroServicio(){};

    public List<Libros> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libros> obtenerLibroPorNombre(String nombre){
        return libroRepository.obtenerLibroPorNombre(nombre);
    }

    public List<Libros>obtenerLibrosPorIdioma(Idiomas idioma){
        return libroRepository.obtenerLibrosPorIdioma(idioma);
    }

    public void guardarLibro(Libros libro){
        libroRepository.save(libro);
    }
}
package com.challenge.literatura.demo;

import com.challenge.literatura.demo.Autores;
import com.challenge.literatura.demo.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AutorServicio {

    public AutorRepositorio autorRepository;

    @Autowired
    public AutorServicio(AutorRepositorio autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorServicio(){};

    public Optional<Autores> obtenerAutorPorNombre(String nombre){
        return autorRepository.obtenerAutorPorNombre(nombre);
    }

    public void guardarAutor(Autores autor){
        autorRepository.save(autor);
    }

    public List<Autores> obtenerTodosLosAutores(){
        return autorRepository.findAll();
    }

    public List<Autores> obtenerAutoresVivosEnAnio(int anio){
        return autorRepository.obtenerAutoresVivosEnAnio(anio);
    }
}
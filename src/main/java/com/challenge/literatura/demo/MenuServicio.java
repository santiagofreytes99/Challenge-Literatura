package com.challenge.literatura.demo;

import com.fasterxml.jackson.core.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class MenuServicio {
    private Http peticionAPI;
    private Scanner sc;
    private LibroServicio libroService;
    private AutorServicio autorService;
    private JsonParser jsonParser;

    @Autowired
    public MenuServicio(LibroServicio libroService, AutorServicio autorService, JsonParser jsonParser) {
        this.peticionAPI = new Http();
        this.sc = new Scanner(System.in);
        this.libroService = libroService;
        this.autorService = autorService;
        this.jsonParser = jsonParser;
    }

    public void guardarLibro() {
        List<LibrosRecord> librosObtenidos = obtenerLibrosApi();

        if (librosObtenidos.isEmpty()) {
            System.out.println("No se encontró ningun libro");
            return;
        }

        System.out.println("Escoja un libro para guardar[0-Cancelar]");
        for (int i = 0; i < librosObtenidos.size(); i++) {
            System.out.println((i + 1) + " - " + librosObtenidos.get(i).titulo() + " - " + librosObtenidos.get(i).idiomas().get(0) + " - " + librosObtenidos.get(i).autores().get(0).nombre());
        }

        int opcion = sc.nextInt();
        sc.nextLine();
        if (opcion == 0) {
            return;
        }
        if (opcion < 1 || opcion > librosObtenidos.size()) {
            System.out.println("Error: número erroneo");
            return;
        }

        LibrosRecord libroRecord = librosObtenidos.get(opcion - 1);
        Optional<Libros> libroTraidoDelRepo = libroService.obtenerLibroPorNombre(libroRecord.titulo());
        Optional<Autores> autorTraidodelRepo = autorService.obtenerAutorPorNombre(libroRecord.autores().get(0).nombre());

        if (libroTraidoDelRepo.isPresent()) {
            System.out.println("Error: no se puede registrar dos veces el mismo libro");
            return;
        }

        Libros libro = new Libros(libroRecord);

        if (!autorTraidodelRepo.isPresent()) {
            Autores autorNuevo = libro.getAutor();
            autorService.guardarAutor(autorNuevo);
        }

        libroService.guardarLibro(libro);
    }

    public List<LibrosRecord> obtenerLibrosApi() {
        System.out.print("Ingrese el título del libro [0-Cancelar]: ");
        String titulo = sc.nextLine();
        if (titulo.equals("0")) {
            return Collections.emptyList();
        }
        List<LibrosRecord> librosObtenidos;
        librosObtenidos = jsonParser.parsearLibros(peticionAPI.obtenerDatos(titulo));
        return librosObtenidos;
    }


    public void listarLibrosRegistrados() {
        List<Libros> libros = libroService.obtenerTodosLosLibros();
        libros.forEach(libro -> libro.imprimirInformacion());
    }

    public void listarAutoresRegistrados() {
        List<Autores> autores = autorService.obtenerTodosLosAutores();
        autores.forEach(autor -> autor.imprimirInformacion());
    }

    public void listarAutoresVivosEnAnio() {
        try {
            System.out.print("Ingrese año: ");
            int anio = sc.nextInt();
            sc.nextLine();
            List<Autores> autores = autorService.obtenerAutoresVivosEnAnio(anio);
            autores.forEach(autor -> autor.imprimirInformacion());
        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número");
        }

    }

    public void listarLibrosPorIdioma() {
        Idiomas.listarIdiomas();
        System.out.print("Ingrese el codigo del idioma [0-Cancelar]: ");
        String idiomaBuscado = sc.nextLine();
        if (idiomaBuscado.equals("0")) {
            return;
        }
        List<Libros> libros = libroService.obtenerLibrosPorIdioma(Idiomas.stringToEnum(idiomaBuscado));
        libros.forEach(libro -> libro.imprimirInformacion());
    }
}
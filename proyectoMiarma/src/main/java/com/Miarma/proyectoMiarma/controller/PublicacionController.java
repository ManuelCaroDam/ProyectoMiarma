package com.Miarma.proyectoMiarma.controller;

import com.Miarma.proyectoMiarma.model.Publicacion;
import com.Miarma.proyectoMiarma.repos.PublicacionRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;

public class PublicacionController {

    private PublicacionRepository repository;

    public void crearPublicacion (@PathVariable String titulo,String texto, Path ruta) {

        Publicacion p = new Publicacion();

        p.setTitulo(titulo);

        p.setTexto(texto);

        p.setFichero(ruta);

    }
}

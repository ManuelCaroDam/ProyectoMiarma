package com.Miarma.proyectoMiarma.controller;

import com.Miarma.proyectoMiarma.model.Publicacion;
import com.Miarma.proyectoMiarma.model.Usuario;
import com.Miarma.proyectoMiarma.repos.PublicacionRepository;
import org.springframework.web.bind.annotation.PathVariable;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class PublicacionController {

    private PublicacionRepository repository;

    public void crearPublicacion (@PathVariable String titulo,String texto, Path ruta) {

        Publicacion p = new Publicacion();

        p.setTitulo(titulo);

        p.setTexto(texto);

        p.setFichero(ruta);

        repository.save(p);

    }

    public void modificarPublicacion(@PathVariable String titulo, Path ruta, Publicacion p) {

        if (titulo != p.getTitulo()) {

            p.setTitulo(titulo);

        }

        if (ruta != p.getFichero()) {

            p.setFichero(ruta);
        }

        repository.save(p);

    }

    public void borrarPublicacion(@PathVariable Publicacion p) {

        repository.delete(p);

    }

    public List<Publicacion> optenerTodasPublicaciones() {
       return repository.findAll();
    }

    public Optional<Publicacion> optenerPublicacion (Long id) {
        return repository.findById(id);
    }

    public List<Publicacion> optenerTodasPublicaciosUsuario(Usuario suUsuario, Usuario miUsuario) {

        if (miUsuario.isRegistrado()) {
            return suUsuario.getListaPublicaciones();
        }else {
            return null;
        }
    }

    public List<Publicacion> optenerPublicacionesUsuarioLogueado (Usuario usuarioLogueado) {

        return usuarioLogueado.getListaPublicaciones();

    }






}

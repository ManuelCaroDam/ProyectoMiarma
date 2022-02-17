package com.Miarma.proyectoMiarma.controller;

import com.Miarma.proyectoMiarma.model.Publicacion;
import com.Miarma.proyectoMiarma.model.Usuario;
import com.Miarma.proyectoMiarma.repos.PublicacionRepository;
import org.springframework.web.bind.annotation.PathVariable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PublicacionController {

    private PublicacionRepository repository;


    //Crea una nueva publicación
    public void crearPublicacion (@PathVariable String titulo,String texto, Path ruta, boolean privacidad) {

        Publicacion p = new Publicacion(titulo,texto,ruta,privacidad);

        repository.save(p);

    }

    //Modifica una publicación
    public void modificarPublicacion(@PathVariable String titulo, Path ruta, Publicacion p, boolean privacidad) {


        //Comprueba campo por campo la publicación editada respecto
        //a la publicación original para ver si hay algún cambio
        if (!Objects.equals(titulo, p.getTitulo())) {

            p.setTitulo(titulo);

        }

        if (ruta != p.getFichero()) {

            p.setFichero(ruta);
        }

        if (privacidad != p.isPublica()) {
            p.setPublica(privacidad);
        }
    // En caso de que hay habido algún cambio, lo realiza y lo guarda
        repository.save(p);

    }

    //Borra una publicación
    public void borrarPublicación(@PathVariable Publicacion p, Usuario usuarioLogueado) {

        List <Publicacion> listaTodasPublicaciones = repository.findAll();
        for (Publicacion listaPublicacion: listaTodasPublicaciones) {

            //Borra la publicación de la lista de publicaciones del usuario
            usuarioLogueado.getListaPublicaciones().remove(p);
        }

        //Borra la publicación del repositorio de publicaciones
        repository.delete(p);
        repository.save(p);

    }




    //Devuelve todas las publicaciones
    public List<Publicacion> obtenerTodasPublicaciones(Usuario usuarioLogueado) {

      return repository.findAll();

    }





    //Obtiene una publicación específica
    public Optional<Publicacion> obtenerUnaPublicacionPorId (Long id) {

        return repository.findById(id);
    }





    public List<Publicacion> obtenerTodasPublicacionesUsuario(Usuario usuarioSeguido, Usuario usuarioLogueado) {

        List<Publicacion> listaPublicacionesPublicas = new ArrayList<>();

        //Se comprueba que el usuario esta logueado
     if (usuarioLogueado.isLogueado()) {

         //Se compruebas si sigues al usuario
         if (usuarioLogueado.getYoSigo().contains(usuarioSeguido)) {

             //Si sigues al usuario te devuelve su lista de publicaciones
             return usuarioSeguido.getListaPublicaciones();
         } else {

             //En caso de que no lo sigas, este for comprueba si cada publicación es pública
             //las publicas las guarda en una lista y las devuelve
             for (int i = 0;i<usuarioSeguido.getListaPublicaciones().size();i++) {

                 if (usuarioSeguido.getListaPublicaciones().get(i).isPublica()) {
                     listaPublicacionesPublicas.add(usuarioSeguido.getListaPublicaciones().get(i));
                     return listaPublicacionesPublicas;
                 }
             }
         }

         }
     return null;

     }




    public List<Publicacion> optenerPublicacionesUsuarioLogueado (Usuario usuarioLogueado) {

        //En caso de que el usuario este logueado devuelve sus publicaciones
        if (usuarioLogueado.isLogueado()) {

            return usuarioLogueado.getListaPublicaciones();
        }
        //En caso que no devuelve null
        return null;
    }






}

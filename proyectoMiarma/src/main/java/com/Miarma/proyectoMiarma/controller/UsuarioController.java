package com.Miarma.proyectoMiarma.controller;

import com.Miarma.proyectoMiarma.model.Usuario;
import com.Miarma.proyectoMiarma.repos.UsuarioRepository;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Objects;

public class UsuarioController {

    private UsuarioRepository repository;

    //Este método visualiza un usuario si tienes acceso a el
    public Usuario visualizarUsuario (@PathVariable Usuario usuarioAVisualizar, Usuario usuarioLogueado) {

        //Comprueba si tu usuario esta logueado en el sistema
       if (usuarioLogueado.isLogueado()) {
           //Comprueba si el pefil que quieres ver es publico
            if (usuarioAVisualizar.isPublicoPerfil())
                return usuarioAVisualizar;
            else {
                //En caso de que no lo sea comprueba si le sigues
                if (usuarioLogueado.getYoSigo().contains(usuarioAVisualizar))
                    return usuarioAVisualizar;
            }
       }


       //Si no es publico y no le sigues no puedes verlo
       return null;
    }
    //Permite editar un usuario
    public void editarUsuario (@PathVariable Usuario usuarioLogueado,String nombreUsuario, boolean publicoPerfil) {


            if (usuarioLogueado.isLogueado()) {

                //Comprueba campo por campo del usuario editado respecto
                //al usuario original para ver si hay algún cambio

                if (!Objects.equals(usuarioLogueado.getNick(), nombreUsuario)) {
                    //En caso de que encuentre algún cambio lo setea
                    usuarioLogueado.setNick(nombreUsuario);

                }

                if (!usuarioLogueado.isPublicoPerfil()) {
                    usuarioLogueado.setPublicoPerfil(publicoPerfil);
                }
            }

        repository.save(usuarioLogueado);

    }

    //Este metodo permite mandar solicitud de seguimiento a un usuario
    public void seguirUsuario (@PathVariable Usuario usuarioASeguir, Usuario usuarioLogueado) {

        if(usuarioLogueado.isLogueado()) {

            //Este método le coloca en la lista de pendientes tu usuario, para que pueda
            //aceptarlo o rechazarlo
            usuarioASeguir.getPendientesAceptar().add(usuarioLogueado);
        }
        repository.save(usuarioASeguir);

    }

    //Este metodo permite aceptar un usuario
    public void aceptarUsuario (@PathVariable Usuario usuarioLogueado, Usuario usuarioAceptar) {

        if (usuarioLogueado.isLogueado()) {

            //Primero borra el usuario de la lista de pendientes
            usuarioLogueado.getPendientesAceptar().remove(usuarioAceptar);
            //Se crea el usuario en la lista de las personas que sigues
            usuarioLogueado.getYoSigo().add(usuarioAceptar);
            //y a la otra persona también se le modifica la lista de personas que le siguen
            usuarioAceptar.getMeSiguen().add(usuarioLogueado);
        }

    }
    //Niega al usuario de seguirle
    public void denegarUsuario (@PathVariable Usuario usuarioLogueado, Usuario usuarioDenegar) {


        if (usuarioLogueado.isLogueado()) {

            //Quita al usuario de la lista de pendientes
            usuarioLogueado.getPendientesAceptar().remove(usuarioDenegar);
        }

    }
    //Devuelve la lista de pendientes de un usuario
    public List<Usuario> listarPendientes (@PathVariable Usuario usuarioLogueado) {

       return usuarioLogueado.getPendientesAceptar();

    }
}

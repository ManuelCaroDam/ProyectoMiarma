package com.Miarma.proyectoMiarma.controller;

import com.Miarma.proyectoMiarma.model.Usuario;
import com.Miarma.proyectoMiarma.repos.UsuarioRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

public class UsuarioController {

    private UsuarioRepository repository;

    public Usuario visualizarUsuario (@PathVariable Usuario usuarioAVisualizar, Usuario usuarioLogueado) {

       if (usuarioLogueado.isLogueado()) {
            if (usuarioAVisualizar.isPublicoPerfil())
                return usuarioAVisualizar;
            else {

                if (usuarioLogueado.getYoSigo().contains(usuarioAVisualizar))
                    return usuarioAVisualizar;
            }


       }
       return null;
    }

    public void editarUsuario (@PathVariable Usuario usuarioLogueado,String nombreUsuario, boolean publicoPerfil) {

        if (usuarioLogueado.isLogueado()) {

            if (usuarioLogueado.isLogueado()) {
                if (!Objects.equals(usuarioLogueado.getNick(), nombreUsuario)) {
                    usuarioLogueado.setNick(nombreUsuario);

                }

                if (!usuarioLogueado.isPublicoPerfil()) {
                    usuarioLogueado.setPublicoPerfil(publicoPerfil);
                }
            }
        }
        repository.save(usuarioLogueado);

    }

    public void seguirUsuario (@PathVariable Usuario usuarioASeguir, Usuario usuarioLogueado) {

        if(usuarioLogueado.isLogueado()) {

            usuarioASeguir.getPendientesAceptar().add(usuarioLogueado);
        }
        repository.save(usuarioASeguir);

    }

    public void aceptarUsuario (@PathVariable Usuario usuarioLogueado, Usuario usuarioAceptar) {

        if (usuarioLogueado.isLogueado()) {
            usuarioLogueado.getPendientesAceptar().remove(usuarioAceptar);
            usuarioLogueado.getYoSigo().add(usuarioAceptar);
            usuarioAceptar.getMeSiguen().add(usuarioLogueado);
        }

    }

    public void denegarUsuario (@PathVariable Usuario usuarioLogueado, Usuario usuarioDenegar) {

        if (usuarioLogueado.isLogueado()) {
            usuarioLogueado.getPendientesAceptar().remove(usuarioDenegar);
        }

    }

    public void listarPendientes (@PathVariable Usuario usuarioLogueado) {

    }


}

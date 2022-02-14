package com.Miarma.proyectoMiarma.controller;

import com.Miarma.proyectoMiarma.model.Usuario;
import org.springframework.web.bind.annotation.PathVariable;

public class UsuarioController {

    public Usuario visualizarUsuario (@PathVariable Usuario suUsuario, Usuario miUsuario) {

       if (suUsuario.isPublicoPerfil()) {
           return suUsuario;
       }
       if (miUsuario.getListaSeguidos().contains(suUsuario)) {
           return suUsuario;
       }
       return null;
    }

    public void editarUsuario (@PathVariable Usuario u,String nombreUsuario, boolean publicoPerfil) {

        if (u.getNombreUsuario() != nombreUsuario) {
            u.setNombreUsuario(nombreUsuario);

        }

        if (u.isPublicoPerfil() == publicoPerfil) {
            u.setPublicoPerfil(publicoPerfil);
        }

    }




}

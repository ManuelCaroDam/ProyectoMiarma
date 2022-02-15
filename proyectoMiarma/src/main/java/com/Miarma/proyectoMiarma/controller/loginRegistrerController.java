package com.Miarma.proyectoMiarma.controller;

import com.Miarma.proyectoMiarma.model.Usuario;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.util.Date;

public class loginRegistrerController {

    public void registrarUsuario (@PathVariable String nick, String email, Date fechaNacimiento, Path avatar, boolean privado) {

        Usuario u = new Usuario(nick, email, fechaNacimiento, avatar, false, privado);


    }


}

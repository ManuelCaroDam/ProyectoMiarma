package com.Miarma.proyectoMiarma.controller;

import antlr.Token;
import com.Miarma.proyectoMiarma.model.Usuario;
import com.Miarma.proyectoMiarma.repos.UsuarioRepository;
import org.springframework.web.bind.annotation.PathVariable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class loginRegisterController {

    private UsuarioRepository repository;

    public Usuario registrarUsuario (@PathVariable String nick, String email, Date fechaNacimiento, Path avatar,
                                  boolean privado) {

        Usuario u = new Usuario(nick, email, fechaNacimiento, avatar, false, privado);
        repository.save(u);

        return u;
    }

    public Token loguearUsuario (@PathVariable Usuario usuarioALoguear) {
        boolean loguear = true;
        Token token = null;
        usuarioALoguear.setLogueado(loguear);

        repository.save(usuarioALoguear);

        return token;

    }

   public List <Usuario> optenerListaUsuariosLogueados () {
        List <Usuario> listaTotalUsuarios = repository.findAll();
        List <Usuario> listaUsuariosLogueados = new ArrayList<>();

        for (int i=0;i<listaTotalUsuarios.size();i++) {

            if (listaTotalUsuarios.get(i).isLogueado()) {
                listaUsuariosLogueados.add(listaTotalUsuarios.get(i));
            }
        }
        return listaUsuariosLogueados;
   }
}

package com.Miarma.proyectoMiarma.controller;

import antlr.Token;
import com.Miarma.proyectoMiarma.model.Usuario;
import com.Miarma.proyectoMiarma.repos.UsuarioRepository;
import org.springframework.web.bind.annotation.PathVariable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class loginRegisterController {

    private UsuarioRepository repository;

    //Este metodo crea un usuario y lo guarda
    public Usuario registrarUsuario (@PathVariable String nick, String email, Date fechaNacimiento, Path avatar,
                                     boolean privado) {

        //Lista con todos los usuarios registrados
        List <Usuario> listaTodosUsuarios = repository.findAll();

        //Comprueba si el nick ya está registrado
        for (Usuario listaTodosUsuario : listaTodosUsuarios) {

            //En caso de que no este registrado, crea el usuario y lo registra
            if (!Objects.equals(listaTodosUsuario.getNick(), nick)) {
                Usuario u = new Usuario(nick, email, fechaNacimiento, avatar, false, privado);
                repository.save(u);
                return u;
            }

        }

        return null;

    }

    //Cambia el parámetro de un usuario para loguearlo
    public Token loguearUsuario (@PathVariable Usuario usuarioALoguear) {
        boolean loguear = true;
        Token token = null;

        //Cambia el atributo booleano asignado como false a true
        //estar en true significa estar logueado
        usuarioALoguear.setLogueado(loguear);

        repository.save(usuarioALoguear);

        return token;

    }

    //Obtiene todos los usuarios que están logueados
   public List <Usuario> optenerListaUsuariosLogueados () {

        List <Usuario> listaTotalUsuarios = repository.findAll();
        List <Usuario> listaUsuariosLogueados = new ArrayList<>();

        //Busca entre todos los usuarios, los que tienen el logueo
       //en true y lo guarda en la lista de logueados
       for (Usuario listaTotalUsuario : listaTotalUsuarios) {

           if (listaTotalUsuario.isLogueado()) {
               listaUsuariosLogueados.add(listaTotalUsuario);
           }
       }
        return listaUsuariosLogueados;
   }
}

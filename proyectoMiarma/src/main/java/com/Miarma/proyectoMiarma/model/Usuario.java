package com.Miarma.proyectoMiarma.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private String nombreUsuario;
    private boolean registrado;
    private boolean publicoPerfil;
    private List <Publicacion> listaPublicaciones;
    private List <Usuario> listaSeguidos;
}

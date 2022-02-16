package com.Miarma.proyectoMiarma.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private String nick;
    private String email;
    private Date fechaNacimiento;
    private Path avatar;
    private boolean logueado;
    private boolean publicoPerfil;
    private List <Publicacion> listaPublicaciones;
    private List <Usuario> yoSigo;
    private List <Usuario> meSiguen;
    private List <Usuario> pendientesAceptar;

    public Usuario(String nick, String email, Date fechaNacimiento, Path avatar, boolean b, boolean privado) {
        this.nick = nick;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.avatar = avatar;
        this.logueado = b;
        this.publicoPerfil = privado;
    }


}

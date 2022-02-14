package com.Miarma.proyectoMiarma.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {

    private String titulo;
    private String texto;
    Path fichero;
}

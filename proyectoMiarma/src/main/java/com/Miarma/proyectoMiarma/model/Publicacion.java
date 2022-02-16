package com.Miarma.proyectoMiarma.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.nio.file.Path;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {

    @Id @GeneratedValue
    private Long id;
    private String titulo;
    private String texto;
    private Path fichero;
    private boolean publica;

    public Publicacion(String titulo, String texto, Path ruta, boolean publico) {
    }
}

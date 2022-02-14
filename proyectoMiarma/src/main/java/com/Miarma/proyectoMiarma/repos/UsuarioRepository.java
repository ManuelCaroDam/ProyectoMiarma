package com.Miarma.proyectoMiarma.repos;

import com.Miarma.proyectoMiarma.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}

package com.Miarma.proyectoMiarma.repos;

import com.Miarma.proyectoMiarma.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,Long> {
}

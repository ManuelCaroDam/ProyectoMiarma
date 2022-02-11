package com.Miarma.proyectoMiarma.users.controller;


import com.Miarma.proyectoMiarma.users.dto.CreateUserDto;
import com.Miarma.proyectoMiarma.users.dto.GetUserDto;
import com.Miarma.proyectoMiarma.users.dto.UserDtoConverter;
import com.Miarma.proyectoMiarma.users.model.UserEntity;
import com.Miarma.proyectoMiarma.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("/auth/register")
    public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newUser) {
        UserEntity saved = userEntityService.save(newUser);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saved));

    }


}

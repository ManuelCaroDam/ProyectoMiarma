package com.Miarma.proyectoMiarma.users.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private String avatar;
    private String fullName;
    private String email;
    private String role;


}

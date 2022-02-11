package com.Miarma.proyectoMiarma.users.services;


import com.Miarma.proyectoMiarma.services.BaseService;
import com.Miarma.proyectoMiarma.users.dto.CreateUserDto;
import com.Miarma.proyectoMiarma.users.model.UserEntity;
import com.Miarma.proyectoMiarma.users.model.UserRole;
import com.Miarma.proyectoMiarma.users.repos.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, UUID, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }


    // Este método lo mejoraremos en el próximo tema
    public UserEntity save(CreateUserDto newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            UserEntity userEntity = UserEntity.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .fullName(newUser.getFullname())
                    .email(newUser.getEmail())
                    .role(UserRole.USER)
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }


}

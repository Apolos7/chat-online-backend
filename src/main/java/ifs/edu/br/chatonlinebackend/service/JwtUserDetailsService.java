package ifs.edu.br.chatonlinebackend.service;

import ifs.edu.br.chatonlinebackend.model.User;
import ifs.edu.br.chatonlinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUsuario = usuarioRepository.findUserByUsername(username);
        User usuario = optUsuario.orElseThrow(() -> new UsernameNotFoundException("username not found"));

        if (usuario.getUsername().equals(username))
            return usuario;
        throw new UsernameNotFoundException("username not found");
    }
}
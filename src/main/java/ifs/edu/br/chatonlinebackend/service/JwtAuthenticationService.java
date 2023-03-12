package ifs.edu.br.chatonlinebackend.service;

import ifs.edu.br.chatonlinebackend.model.User;
import ifs.edu.br.chatonlinebackend.model.dto.LoginDTO;
import ifs.edu.br.chatonlinebackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String autenticarUsuario(LoginDTO loginDTO) throws Exception {
        authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        User usuarioDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
        return jwtTokenUtil.generateToken(usuarioDetails);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Your account is disabled, contact support");
        } catch (BadCredentialsException e) {
            throw new Exception("username or/and password are incorrect");
        }
    }

}

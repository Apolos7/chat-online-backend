package ifs.edu.br.chatonlinebackend.controller;

import ifs.edu.br.chatonlinebackend.config.filters.JwtAuthRequestFilter;
import ifs.edu.br.chatonlinebackend.model.dto.SignUpDTO;
import ifs.edu.br.chatonlinebackend.service.JwtAuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SignUpDTO authenticationRequest, HttpServletResponse response) throws Exception {
        String token = jwtAuthenticationService.autenticarUsuario(authenticationRequest);
        response.addHeader(JwtAuthRequestFilter.AUTHORIZATION_HEADER, token);
        return ResponseEntity.ok().build();
    }

}
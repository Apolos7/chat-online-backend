package ifs.edu.br.chatonlinebackend.controller;

import ifs.edu.br.chatonlinebackend.model.User;
import ifs.edu.br.chatonlinebackend.model.dto.SignInDTO;
import ifs.edu.br.chatonlinebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<SignInDTO> createAuthenticationToken(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.insertUser(user));
    }

}

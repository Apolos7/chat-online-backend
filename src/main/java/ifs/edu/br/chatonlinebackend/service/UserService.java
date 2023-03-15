package ifs.edu.br.chatonlinebackend.service;

import ifs.edu.br.chatonlinebackend.model.User;
import ifs.edu.br.chatonlinebackend.model.dto.SignInDTO;
import ifs.edu.br.chatonlinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public SignInDTO insertUser(User user) {
        return userRepository.save(user).toSignInDto();
    }

}

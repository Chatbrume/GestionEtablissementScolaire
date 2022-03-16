package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.domain.Roles;
import eu.ensup.gestionetablissement.domain.User;
import eu.ensup.gestionetablissement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public User signup(User user) {
        user.setRole(Roles.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);
        return result;
    }*/

    public User signin(User user) {
        User result = userRepository.findByUsername(user.getUsername()).get();
        result.setPassword(passwordEncoder.encode(user.getPassword()));
        return result;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

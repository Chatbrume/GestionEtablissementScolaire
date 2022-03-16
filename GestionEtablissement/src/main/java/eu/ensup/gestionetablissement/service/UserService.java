package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.domain.User;
import eu.ensup.gestionetablissement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && ! user.isActivate())
            return null;

        return user;
    }

    public UserDetails loadAllUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAllByUsername(username).orElse(null);
    }

    public boolean save(User user) {
        return userRepository.save(user) != null;
    }

    public User getOne(Long userID) {
        User user = userRepository.findById(userID).orElse(null);
        if (user != null)
            user.setPassword(getEncodedPassword(user.getPassword()));

        return user;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
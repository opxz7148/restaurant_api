package ku.restaurant.service;

import ku.restaurant.dto.SignUpRequest;
import ku.restaurant.entity.User;
import ku.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public void createUser(SignUpRequest request) {
        User dao = new User();

        dao.setUsername(request.getUsername());
        dao.setPassword(passwordEncoder.encode(request.getPassword()));
        dao.setName(request.getName());

        dao.setCreateAt(Instant.now());
        dao.setRole("ROLE_USER");
        userRepository.save(dao);
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }


}

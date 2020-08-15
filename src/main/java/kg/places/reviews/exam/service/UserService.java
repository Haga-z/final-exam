package kg.places.reviews.exam.service;


import kg.places.reviews.exam.DTO.UserResponseDTO;
import kg.places.reviews.exam.exception.UserAlreadyRegisteredException;
import kg.places.reviews.exam.model.User;
import kg.places.reviews.exam.model.UserRegisterForm;
import kg.places.reviews.exam.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserResponseDTO register(UserRegisterForm form) {
        if (userRepository.existsByEmail(form.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }

        var user = User.builder()
                .email(form.getEmail())
                .fullname(form.getName())
                .password(encoder.encode(form.getPassword()))
                .build();

        userRepository.save(user);

        return UserResponseDTO.from(user);
    }

    public User findLoggedInUser(Principal principal) {
        return userRepository.findByEmail(principal.getName());
    }
}

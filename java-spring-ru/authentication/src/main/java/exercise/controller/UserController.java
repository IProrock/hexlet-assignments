package exercise.controller;

import exercise.dto.UserDto;
import exercise.model.User;
import exercise.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // Кодировщик BCrypt
    // Используйте для хеширования пароля
    private final PasswordEncoder encoder;

    @GetMapping(path = "")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public User register(@RequestBody UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.email());
        user.setUsername(userDto.username());
        String password = encoder.encode(userDto.password());
        user.setPassword(password);
        return userRepository.save(user);
    }
    // END
}

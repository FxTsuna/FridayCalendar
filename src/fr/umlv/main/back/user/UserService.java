package fr.umlv.main.back.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public ResponseEntity<UserResponseDTO> addUser(String username, byte[] password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        var user = new User(username, password);
        var createdUser =  userRepository.save(user);
        return ResponseEntity
                .created(URI.create("/users/save/" + createdUser.getId()))
                .body(new UserResponseDTO(createdUser.getId(), createdUser.getUsername()));
    }

    public ResponseEntity<UserResponseDTO> removeUser(UUID id, byte[] password) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(password);
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserResponseDTO> updatePassword(UUID id , byte[] newPassword) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(newPassword);
        var userContainer = userRepository.findById(id);
        if (userContainer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var user = userContainer.get();
        user.setPassword(newPassword);
        var updatedUser = userRepository.save(user);
        return ResponseEntity
                .created(URI.create("/users/update/" + updatedUser.getId()))
                .body(new UserResponseDTO(updatedUser.getId(), updatedUser.getUsername()));
    }

    public ResponseEntity<UserResponseDTO> getUserById(UUID id) {
        Objects.requireNonNull(id);
        var userContainer = userRepository.findById(id);
        if (userContainer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var user = userContainer.get();
        return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getUsername()));
    }

    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        var users = userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getUsername()))
                .toList();
        return ResponseEntity.ok(users);
    }
}

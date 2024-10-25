package com.pragma.redis.controller;

import com.pragma.redis.redistest.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class JedisController {

    private List<User> users = new ArrayList<>();

    // Endpoint para crear un usuario (POST)
    @PostMapping
    public String createUser(@RequestBody User user) {
        users.add(user);
        return "Usuario creado con éxito!";
    }

    // Endpoint para obtener todos los usuarios (GET)
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Endpoint para obtener un usuario por ID (GET)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user.orElse(null);
    }

    // Endpoint para actualizar un usuario (PUT)
    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        Optional<User> existingUserOpt = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            // Actualiza los detalles del usuario
            existingUser.setName(updatedUser.getName());
            existingUser.setLastName(updatedUser.getLastName());
            return "Usuario actualizado con éxito!";
        } else {
            return "Usuario no encontrado";
        }
    }

    // Endpoint para eliminar un usuario (DELETE)
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (removed) {
            return "Usuario eliminado con éxito!";
        } else {
            return "Usuario no encontrado";
        }
    }
}

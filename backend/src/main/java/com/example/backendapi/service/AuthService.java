/*
package com.example.backendapi.service;

import com.example.backendapi.model.User;
import com.example.backendapi.repository.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String SECRET_KEY = "mySecretKey"; // Keep this secret

    public ResponseEntity<String> signUp(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already registered!");
        }

        // Create new user
        User newUser = new User();
        newUser.setEmail(user.getEmail().toLowerCase()); // Ensure email is lowercase
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<String> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            // Return the stored token with a successful response
            return ResponseEntity.ok(user.get().getToken());
        }

        // Return unauthorized status with error message
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
    }

    public String resetPassword(String email, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // Check if the user is present
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();  // Get the User object from Optional

            user.setPassword(passwordEncoder.encode(newPassword));  // Encode new password

            // Generate a new token after password reset
            String newToken = generateToken(user.getEmail());
            user.setToken(newToken);

            // Save the updated User object
            userRepository.save(user);  // Save the user with new password and token

            return "Password reset successfully!";
        }
        return "User not found!";
    }



    // Generate a secure key
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10-hour expiry
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
*/

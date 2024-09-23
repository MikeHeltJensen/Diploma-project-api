/*
package com.example.backendapi.controller;

import com.example.backendapi.model.User;
import com.example.backendapi.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public class UserController {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtTokenProvider jwtTokenProvider;

        @GetMapping("/user")
        public ResponseEntity<User> getUserDetails(HttpServletRequest request) {
            String token = jwtTokenProvider.resolveToken(request);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                String email = jwtTokenProvider.getEmailFromToken(token);
                Optional<User> user = userRepository.findByEmail(email);
                if (user.isPresent()) {
                    return ResponseEntity.ok(user.get());
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
*/

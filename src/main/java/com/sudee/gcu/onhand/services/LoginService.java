/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/23/2021
 */

package com.sudee.gcu.onhand.services;

import com.sudee.gcu.onhand.models.User;
import com.sudee.gcu.onhand.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return password.equals(user.getPassword());
    }
}

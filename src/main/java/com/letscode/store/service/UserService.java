package com.letscode.store.service;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.dto.UserDTO;
import com.letscode.store.exceptions.InvalidValueFieldException;
import com.letscode.store.model.Authority;
import com.letscode.store.model.Client;
import com.letscode.store.model.User;
import com.letscode.store.repository.AuthorityRepository;
import com.letscode.store.repository.ProductRepository;
import com.letscode.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthorityRepository authorityRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDTO userDTO) {

        if (userDTO.getPassword() == null) {
            throw new InvalidValueFieldException("Password não pode ser nulo!");
        }
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
            throw new InvalidValueFieldException("User já existe!");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = User.convert(userDTO);
        final User userDB = userRepository.save(user);
        userDTO.getRoles()
                .forEach(a -> saveAuthority(userDB, a));
    }

    public void saveAuthority(User user, String role) {
        Authority authority = Authority.convert(user, "ROLE_" + role);
        authorityRepository.save(authority);
    }
}

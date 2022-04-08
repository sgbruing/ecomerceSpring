package com.example.ProjetoModuloBD.service;

import com.example.ProjetoModuloBD.dto.UserRequest;
import com.example.ProjetoModuloBD.model.Authority;
import com.example.ProjetoModuloBD.model.AuthorityKey;
import com.example.ProjetoModuloBD.model.User;
import com.example.ProjetoModuloBD.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public void createAuthority(UserRequest userRequest, User user){
        Authority authority = new Authority();

        authority.setAuthorityKey(new AuthorityKey(userRequest.getUserName(), "ROLE_" + userRequest.getAuthority()));
        authority.setUser(user);

        authorityRepository.save(authority);
    }
}

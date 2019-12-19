package com.netcracker.fapi.services.impl;

import com.netcracker.fapi.services.UserService;
import com.netcracker.fapi.viewmodels.UserVM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service("customUserDetailsService")
@NoArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String URL = "http://localhost:8080/api/users";
    private final RestTemplate restTemplate = new RestTemplate();

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity login(@Valid UserVM userVM) {
        HttpEntity<UserVM> entity = new HttpEntity<>(userVM);
        ResponseEntity<UserVM> model = restTemplate.postForEntity(URL + "/login", entity, UserVM.class);
        return new ResponseEntity<>(model.getBody(), HttpStatus.OK);
    }

    public ResponseEntity current() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserVM byLogin = findByLogin(((User) authentication.getPrincipal()).getUsername());
        return new ResponseEntity<>(byLogin, HttpStatus.OK);
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserVM user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    @Override
    public ResponseEntity findById(@NotNull Long id) {
        UserVM model = restTemplate.getForObject(URL + "/" + id, UserVM.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity create(@Valid UserVM userVM) {
        HttpEntity<UserVM> entity = new HttpEntity<>(userVM);
        userVM.setPassword(bCryptPasswordEncoder.encode(userVM.getPassword()));
        ResponseEntity<UserVM> response;
        try {
            response = restTemplate.postForEntity(URL, entity, UserVM.class);
        } catch (HttpClientErrorException.BadRequest e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity delete(@NotNull Long id) {
        UserVM model = restTemplate.getForObject(URL + "/" + id, UserVM.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(@NotNull Long id, @Valid UserVM userVM) {
        HttpEntity<UserVM> entity = new HttpEntity<>(userVM);
        Map<String, Long> pathVar = new HashMap<>();
        pathVar.put("id", id);
        HttpEntity<UserVM> httpModel = restTemplate.exchange(URL + "/" + id,
                HttpMethod.PUT,
                entity, UserVM.class,
                pathVar);
        return new ResponseEntity<>(httpModel, HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVM user = findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
    }

    @Override
    public UserVM findByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL + "/login/" + login, UserVM.class);
    }

}

package com.example.sqlstore.config;

import com.example.sqlstore.login.User;
import com.example.sqlstore.login.UserRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;


// pobranie danych o użytkowniku, przetłumaczenie na format rozumiany przez Stringa i zwrócenie tych danych
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)  //tranzakcja tylko do odczytu
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); //pobranie użytkownika i listy jego uprawnień po nazwie

        if (user == null) {
            throw new UsernameNotFoundException("Login failed");
        }

        Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());

        //Set<GrantedAuthority> grantedAuthorities = Sets.newHashSet(); //tworzenie repozytoriów typu SimpleGrantedAuthority
        //user.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()))); // pobieranie i dodanie ról


        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities); // zwrócenie nowego użytkownika
        //przepisanie uprawnień z bazy
    }
}

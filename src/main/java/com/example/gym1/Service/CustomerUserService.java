package com.example.gym1.Service;

import com.example.gym1.Model.Role;
import com.example.gym1.Model.User;
import com.example.gym1.Repo.RoleRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomerUserService implements UserDetailsService {
    private final UserRepo userRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepo roleRepo;
    public CustomerUserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepo.findUserByEmail(email).orElseThrow(()->new UsernameNotFoundException("User with this email not found"+email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
package com.example.gym1.Service;

import com.example.gym1.Config.JwtUtils;
import com.example.gym1.Dto.APIResponse;
import com.example.gym1.Dto.LogIn;
import com.example.gym1.Repo.RoleRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogInService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    CustomerUserService customerUserService;

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserService userService;
    @Autowired
 private    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;


    public Object login(LogIn logIn){
        APIResponse apiResponse=new APIResponse();
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    logIn.getEmail(), logIn.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateJwt(userRepo.findUserByEmail(logIn.getEmail()).get());
            apiResponse.setAccessToken(token);
            apiResponse.setUserData(userRepo.findUserByEmail(logIn.getEmail()).get());



            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (BadCredentialsException badCredentialsException){




            return new ResponseEntity<>("bad credencials",HttpStatus.BAD_REQUEST);
        }

    }
}



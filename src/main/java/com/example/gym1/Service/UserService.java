package com.example.gym1.Service;

import com.example.gym1.Converter.UserToUserDto;
import com.example.gym1.Dto.PasswordDto;
import com.example.gym1.Dto.UserDto;
import com.example.gym1.Dto.UserRegistrationDto;
import com.example.gym1.Model.Gender;
import com.example.gym1.Model.Role;
import com.example.gym1.Model.User;
import com.example.gym1.Repo.RoleRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PasswordEncoder passwordEncoder;
    private final UserToUserDto userToUserDto;

    public UserService(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    public ResponseEntity<?> save(UserRegistrationDto userRegistrationDto) {
        String event = "New User is created!";
        User userToAdd = new User();

        if (userRepo.findUserByEmail(userRegistrationDto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        } else {

            userToAdd.setEmail(userRegistrationDto.getEmail());
            userToAdd.setUserName(userRegistrationDto.getUserName());
            userToAdd.setLastName(userRegistrationDto.getLastName());
            userToAdd.setPhoneNumber(userRegistrationDto.getPhoneNumber());
            userToAdd.setAge(userRegistrationDto.getAge());
            userToAdd.setAddress(userRegistrationDto.getAddress());
            userToAdd.setPasswordToken(generateToken());
            userToAdd.setGender(Gender.Female);
            addUserRole(userToAdd);

            userRepo.save(userToAdd);
            return new ResponseEntity<>(userToAdd.getPasswordToken(), HttpStatus.OK);
        }

    }

    //add password by token
    public String addPasswordByToken(String token, PasswordDto passwordDto) {
        User user = userRepo.findByPasswordToken(token);
        user.setPassword(passwordDto.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(passwordDto.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "Password added correct!;";

    }

    //updatePassword
    public String upddatePassword(PasswordDto passwordDto, Long id) {
        User userToUpdate = userRepo.findById(id).get();
        userToUpdate.setPassword(passwordDto.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(passwordDto.getPassword());
        userToUpdate.setPassword(encodedPassword);
        userRepo.save(userToUpdate);
        return "Passwordi u ndryshua me sukses!";
    }


    public void addUserRole(User user) {
        Role userRole = roleRepo.findByName("user");
        user.addRole(userRole);
    }

    public void addAdminRole(User user) {
        Role adminRole = roleRepo.findByName("admin");
        user.addRole(adminRole);
    }


    private String generateToken() {
        StringBuilder token = new StringBuilder();
        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();


    }

    //update user data by id
    public ResponseEntity<?> updateUser(UserDto userDto, Long id) {
        User userToUpdate = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("This use does not found!"));
        userToUpdate.setUserName(userDto.getUserName());
        userToUpdate.setLastName(userToUpdate.getLastName());
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setPhoneNumber(userToUpdate.getPhoneNumber());
        userRepo.save(userToUpdate);
        return new ResponseEntity<>("Data updated success!", HttpStatus.OK);

    }

    //find User by id
    public UserDto getById(String id) {
        long parseId;
        try {

            parseId = Long.parseLong(id);

        } catch (NumberFormatException exception) {
            throw new NumberFormatException("This id can't be parsed!");
        } catch (NullPointerException e) {
            throw new NumberFormatException("Not found!");
        }

        return userToUserDto.convert(userRepo.findById(parseId).orElseThrow(()->new NoSuchElementException("Not Found")));

    }
    public List<UserDto>findAll(){
        return userRepo.findAll().stream().map(userToUserDto::convert).collect(Collectors.toList());
    }


    public ResponseEntity<?>deleteById(String id){
long parseId;
try {
    parseId=Long.parseLong(id);

}catch ( NumberFormatException e){
    throw new NumberFormatException("This id can't be parsed");

}
userRepo.deleteById(parseId);


        return new ResponseEntity<>("User deleted!",HttpStatus.OK);
    }

}

package com.example.gym1.Service;

import com.example.gym1.Converter.RateDtoToRate;
import com.example.gym1.Converter.RateToRateDto;
import com.example.gym1.Converter.UserDtoToUser;
import com.example.gym1.Converter.UserToUserDto;
import com.example.gym1.Dto.PasswordDto;
import com.example.gym1.Dto.RateDto;
import com.example.gym1.Dto.UserDto;
import com.example.gym1.Dto.UserRegistrationDto;
import com.example.gym1.Model.*;
import com.example.gym1.Repo.RateRepo;
import com.example.gym1.Repo.RoleRepo;
import com.example.gym1.Repo.UserRepo;
import com.example.gym1.Utils.GenderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    private final UserDtoToUser toUser;
    private final RateToRateDto rateToRateDto;
    private final RateDtoToRate toRate;

    @Autowired
    RateRepo rateRepo;
    private final UserToUserDto userToUserDto;

    public UserService(UserDtoToUser toUser, RateToRateDto rateToRateDto, RateDtoToRate toRate, UserToUserDto userToUserDto) {
        this.toUser = toUser;
        this.rateToRateDto = rateToRateDto;
        this.toRate = toRate;
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
            userToAdd.setGender(GenderUtils.getGender(userRegistrationDto.getGender()));
            addUserRole(userToAdd);
            userRepo.save(userToAdd);
            return new ResponseEntity<>(userToAdd.getPasswordToken(), HttpStatus.OK);
        }
    }

   public ResponseEntity<?> saveUser(UserDto userDto){
        User userToSave=toUser.convert(userDto);
String encodedPassword=bCryptPasswordEncoder.encode(userDto.getPassword());
userToSave.setPassword(encodedPassword);
userToSave.setPasswordToken(generateToken());
       userRepo.save(userToSave);
       return new ResponseEntity<>(userToSave.getPasswordToken(),HttpStatus.OK);
    }


    //add password by token
    public String addPasswordByToken(String token, PasswordDto passwordDto) {
        User user = userRepo.findByPasswordToken(token);
        user.setPassword(passwordDto.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(passwordDto.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "Password added correct!";
    }

    //updatePassword
    public String upddatePassword(PasswordDto passwordDto, Long id) {
        User userToUpdate = userRepo.findById(id).get();
        userToUpdate.setPassword(passwordDto.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(passwordDto.getPassword());
        userToUpdate.setPassword(encodedPassword);
        userRepo.save(userToUpdate);
        return "Password updated success!";
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
           public ResponseEntity<?> updateUser(Long id,UserDto userDto) {
        try {
            User userToUpdate = userRepo.findById(id).get();
            userToUpdate.setUserName(userDto.getUserName());
            userToUpdate.setLastName(userToUpdate.getLastName());
            userToUpdate.setEmail(userDto.getEmail());
            userToUpdate.setPhoneNumber(userToUpdate.getPhoneNumber());
            userRepo.save(userToUpdate);
            return new ResponseEntity<>("Data updated success!", HttpStatus.OK);

        } catch (NoSuchElementException elementException) {
            return new ResponseEntity<>("This user does not exist!", HttpStatus.BAD_REQUEST);
        }
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

        return userToUserDto.convert(userRepo.findById(parseId).orElseThrow(()->new NoSuchElementException("Not Found!")));

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
    //user add rate for the gym
    public String addRate(Rate rate){
        User userToAddRate=userRepo.findById(rate.getUser().getId()).get();
        Rate rate1=new Rate();
        rate1.setUser(userToAddRate);
        rate1.setComment(rate.getComment());
        rate1.setRate(rate.getRate());
        rateRepo.save(rate1);
        return "Rate added success!";

    }//get Rate By user id
public void  addRate(RateDto rateDto){
        Rate rateToAdd=toRate.convert(rateDto);
        rateRepo.save(rateToAdd);
}
    public Optional<Rate> getRateById(String id){
        long parseId;
        try {
            parseId = Long.parseLong(id);
        }
                catch(NumberFormatException exception){
            throw new NumberFormatException("This id can't be parsed!");
            }
        return rateRepo.findById(parseId);
        }
public  List<RateDto>findAllRates(){
        return rateRepo.findAll().stream().map(rate -> rateToRateDto.convert(rate)).collect(Collectors.toList());
}
public Object saveRate(RateDto rateDto){
        Rate rateToSave=toRate.convert(rateDto);
        return rateRepo.save(rateToSave);
}
    }



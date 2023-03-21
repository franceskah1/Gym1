package com.example.gym1.Controller;

import com.example.gym1.Dto.PasswordDto;
import com.example.gym1.Dto.UserDto;
import com.example.gym1.Dto.UserRegistrationDto;
import com.example.gym1.Model.User;
import com.example.gym1.Service.TranierService;
import com.example.gym1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("app/user/registration")
public class MemberController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.save(userRegistrationDto);

    }
    @PostMapping("addPassword/{passwordToken}")
    public String addPasswordByToken(@PathVariable("passwordToken") String passwordToken, @RequestBody PasswordDto passwordDto){
        return userService.addPasswordByToken(passwordToken,passwordDto);
    }
    //updatePassword
    @PostMapping("changePassWord/{id}")
    public String changePassword(@RequestBody PasswordDto passwordDto,@PathVariable("id")Long id){
        return userService.upddatePassword(passwordDto, id);


    }
    @PutMapping("updateUser/{id}")
    public ResponseEntity<?>updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        return userService.updateUser(userDto, id);

    }
    @GetMapping("getUserByID/{id}")
    public UserDto getById(@PathVariable("id")String id){
        return userService.getById(id);

    }
    @GetMapping("getAll")
    public List<UserDto>getAll(){
        return userService.findAll();

    }
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?>deleteById(@PathVariable("id")String id){
        return userService.deleteById(id);

    }


}
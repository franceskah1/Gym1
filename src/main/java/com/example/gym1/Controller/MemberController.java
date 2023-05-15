package com.example.gym1.Controller;

import com.example.gym1.Dto.*;
import com.example.gym1.Model.Attendance;
import com.example.gym1.Model.HealthStatus;
import com.example.gym1.Model.Rate;
import com.example.gym1.Service.AttendanceService;
import com.example.gym1.Service.HealthStatusService;
import com.example.gym1.Service.LogInService;
import com.example.gym1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("app/user/registration")
public class MemberController {
    @Autowired
    UserService userService;
@Autowired
    LogInService logInService;
@Autowired
    HealthStatusService healthStatusService;

@Autowired
    AttendanceService attendanceService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.save(userRegistrationDto);

    }
    @PostMapping("/registerUser")
    public ResponseEntity<?> registerMember(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

        @PostMapping("/login")
    public Object authenticateUser(@RequestBody com.example.gym1.Dto.LogIn logIn) {
        Object apiResponse = logInService.login(logIn);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

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
        return userService.updateUser(id, userDto);

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
    //user add health status
    @PostMapping("saveHealthStatus")
    public String addHealthStatus(@RequestBody HealthStatus healthStatus){
        return healthStatusService.addHealthStatus(healthStatus);
    }
@GetMapping("getHealthStatusById/{id}")
    public HealthStatusDto getHealthStatusById(@PathVariable("id")String id){
        return healthStatusService.getHealthStatus(id);

}
@GetMapping("getHealthStatusByUserId/{userId}")
    public HealthStatusDto getHealthStatusByUserId(@PathVariable("userId")Long userId){
        return healthStatusService.getHealthStatusById(userId);

}
@PostMapping("postAttendance")
    public String postAttendance(@RequestBody Attendance attendance){
        return attendanceService.addAttendance(attendance);
}
@GetMapping("getAttendanceById/{id}")
    public AttendanceDto getAttendanceById(@PathVariable("id")String id){
        return attendanceService.getAttendanceById(id);

}
@PostMapping("addRate")
    public String userAddRate(@RequestBody Rate rate){
        return userService.addRate(rate);


}

@GetMapping("getRateById/{id}")
    public Optional<Rate> getRateById(@PathVariable("id")String id) {
  return userService.getRateById(id);



}
@GetMapping("findAllRates")
    public List<RateDto>findAll(){
        return userService.findAllRates();

}
@PostMapping("saveRate")
    public Object saveRate(@RequestBody RateDto rateDto){
        return userService.saveRate(rateDto);
}

}
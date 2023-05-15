package com.example.gym1.Controller;

import com.example.gym1.Dto.StaffDto;
import com.example.gym1.Model.Staff;
import com.example.gym1.Service.LogInService;
import com.example.gym1.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gymApp/admin")
public class StaffController {
    //save staff
    
    @Autowired
    StaffService staffService;

    @PostMapping("save/staff")
    public ResponseEntity<?> saveStaff(@RequestBody StaffDto staffDto) {
        return staffService.addStaff(staffDto);
    }

    //getStaffBy id
    @GetMapping("getStaffById/{id}")
    public Staff getStaffById(@PathVariable("id") String id) {
        return staffService.getById(id);
    }

    //get All STAFF

    @GetMapping("getAll")
    public List<Staff> getAll() {
        return staffService.getAll();
    }




    }

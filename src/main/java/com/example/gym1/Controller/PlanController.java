package com.example.gym1.Controller;

import com.example.gym1.Dto.ClassDto;
import com.example.gym1.Model.GymPlan;
import com.example.gym1.Service.ClassService;
import com.example.gym1.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gymApp/admin")
public class PlanController {
    @Autowired
    PlanService planService;
    @Autowired
    ClassService classService;
//admin create a new plan
    @PostMapping("createPlane")
    public String createPlan(@RequestBody GymPlan gymPlan){
        return planService.createPlan(gymPlan);
    }

    //admin get plan by id and update id

    @PutMapping("updatePlan/{id}")
    public ResponseEntity<?>updatePlane(@PathVariable("id")Long id,@RequestBody GymPlan gymPlan){
        return planService.updatePlan(id, gymPlan);

    }
    //delete plan by id

//TODO fixed cascade type

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?>deleteById(@PathVariable("id")String id){
        return planService.deleteById(id);

    }
    //get all plans

    @GetMapping("getAllPlan")
    public List<GymPlan> getAll(){
        return planService.getAll();
    }
    //get plan by id
    @GetMapping("getPlanById/{id}")
    public GymPlan getPlanById(@PathVariable("id")String id){
        return planService.getById(id);
    }

    //add a new class bt admin
    @PostMapping("registerClass")
    public String addClass(@RequestBody ClassDto classDto){
        return classService.createClass(classDto);
    }


    @GetMapping("getClassById/{id}")
    public ClassDto getById(@PathVariable("id")String id){
        return classService.getClassById(id);
    }


    @GetMapping("get/allClasses")
    public List<ClassDto>findAll(){
        return classService.getAll();
    }
//    @DeleteMapping("deleteById/{id}")
//    public ResponseEntity<?>deleteClassById(@PathVariable("id")String id){
//        return classService.deleteClassById(id);

    }


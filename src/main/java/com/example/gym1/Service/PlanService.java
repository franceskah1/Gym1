package com.example.gym1.Service;

import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.GymPlan;
import com.example.gym1.Repo.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PlanService {
    @Autowired
    PlanRepo planRepo;

    //admin create plan
    public String createPlan(GymPlan plan) {
        GymPlan gymPlan = new GymPlan();
        gymPlan.setPlan_name(plan.getPlan_name());
        gymPlan.setDescription(plan.getDescription());
        gymPlan.setDuration_months(plan.getDuration_months());
        gymPlan.setPrice(plan.getPrice());
        planRepo.save(gymPlan);
        return "Plane saved success!";
    }

    //get all plans
    public List<GymPlan> getAll() {
        return planRepo.findAll();
    }

    //get plan by id
    public GymPlan getById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("This id can't be parsed!");
        }
        return planRepo.findById(parseId).orElseThrow(() -> new NotFoundException("Plan with this Id:" + parseId + " does not exist!"));

    }
//admin delete a plan
    public ResponseEntity<?> deleteById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("This id can't be parsed!");
        }
        planRepo.deleteById(parseId);
        return new ResponseEntity<>("Plan deleted success!", HttpStatus.OK);

    }
    //update plane
    public ResponseEntity<?>updatePlan(Long id,GymPlan gymPlan){
        if(planRepo.existsById(id)){
            GymPlan gymPlan1=planRepo.findById(id).orElseThrow(()-> new NotFoundException("Plan with this id:" + id + "does not exist!"));
            gymPlan1.setPrice(gymPlan.getPrice());
            gymPlan1.setPlan_name(gymPlan.getPlan_name());
            gymPlan1.setDescription(gymPlan.getDescription());
            gymPlan1.setDuration_months(gymPlan.getDuration_months());
            gymPlan.setPrice(gymPlan.getPrice());
            planRepo.save(gymPlan1);
            return new ResponseEntity<>("Plan updated success!",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not Found!",HttpStatus.BAD_REQUEST);
        }
    }
}


package com.example.gym1.Service;

import com.example.gym1.Model.Tranier;
import com.example.gym1.Repo.TranierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TranierService {
    @Autowired
    TranierRepo tranierRepo;
   public ResponseEntity<?>addTrainer(Tranier tranier){
  Tranier tranier1=new Tranier();
  tranier1.setFirstName(tranier.getFirstName());
  tranier1.setLastName(tranier.getLastName());
  tranier1.setEmail(tranier.getEmail());
  tranier1.setSalary(tranier.getSalary());
tranier1.setHiredDate(tranier.getHiredDate());
tranier1.setPhoneNumber(tranier.getPhoneNumber());
tranierRepo.save(tranier1);
return new ResponseEntity<>("Tranier saved Sucess!", HttpStatus.OK);
       }


    }




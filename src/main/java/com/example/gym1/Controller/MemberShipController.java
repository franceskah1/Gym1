package com.example.gym1.Controller;

import com.example.gym1.Dto.NotificationDto;
import com.example.gym1.Model.MemberShip;
import com.example.gym1.Repo.MemberShipRepo;
import com.example.gym1.Service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/user")
public class MemberShipController {
    @Autowired
    MemberShipService memberShipService;
    @Autowired
    MemberShipRepo memberShipRepo;

    @PostMapping("save/membership")
    public ResponseEntity<?> saveMemberShip(@RequestBody MemberShip memberShip){
        return memberShipService.createMemberShip(memberShip);

    }
    @PutMapping("updateMemberShip/{id}")
    public ResponseEntity<?>updateMemberShip(@PathVariable("id")Long id,@RequestBody MemberShip memberShip){
        return memberShipService.updateMemberShipType(id, memberShip);
    }
    @DeleteMapping("deleteMemberShip/{id}")
    public ResponseEntity<?> deleteMemberShip(@PathVariable("id")Long id){
   return  memberShipService.deleteMemberShipByid(id);
    }
@GetMapping("getNotification")
    public List<NotificationDto>findAll(){
        return memberShipService.getAllNotification();

}
@GetMapping("getNotificationByID{id}")
    public NotificationDto getById(@PathVariable("id")String id){
        return memberShipService.getNotificationById(id);
}

}

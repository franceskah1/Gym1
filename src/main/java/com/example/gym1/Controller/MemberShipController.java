package com.example.gym1.Controller;

import com.example.gym1.Dto.NotificationDto;
import com.example.gym1.Dto.PaymentDto;
import com.example.gym1.Model.MemberShip;
import com.example.gym1.Model.Payments;
import com.example.gym1.Repo.MemberShipRepo;
import com.example.gym1.Repo.NotifyRepo;
import com.example.gym1.Service.MemberShipService;
import com.example.gym1.Service.PaymentService;
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
    @Autowired
    NotifyRepo notifyRepo;
@Autowired
    PaymentService paymentService;

    @PostMapping("save/membership")
    public ResponseEntity<?> saveMemberShip(@RequestBody MemberShip memberShip) {
        return memberShipService.createMemberShip(memberShip);

    }

    @PutMapping("updateMemberShip/{id}")
    public ResponseEntity<?> updateMemberShip(@PathVariable("id") Long id, @RequestBody MemberShip memberShip) {
        return memberShipService.updateMemberShipType(id, memberShip);
    }

    @DeleteMapping("deleteMemberShip/{id}")
    public ResponseEntity<?> deleteMemberShip(@PathVariable("id") Long id) {
        return memberShipService.deleteMemberShipByid(id);
    }

    @GetMapping("getNotification")
    public List<NotificationDto> findAll() {
        return memberShipService.getAllNotification();

    }

    @GetMapping("getNotificationByID{id}")
    public NotificationDto getById(@PathVariable("id") String id) {
        return memberShipService.getNotificationById(id);
    }


    @GetMapping("getTotalMemberShip")
    public Long getAll() {
        return memberShipRepo.count();
    }
@PostMapping("payment")
    public String addPayment(@RequestBody Payments payments){
     return    paymentService.doPayment(payments);

}
//get a specific payment by id
@GetMapping("getPaymentById/{id}")
    public PaymentDto getPayment(@PathVariable("id")String id){
        return paymentService.getPaymentById(id);
}
//delete Paymeny by id

    @DeleteMapping("deleteBYId/{id}")
    public String deleteById(@PathVariable("id")Long id){
        return paymentService.deletePayment(id);

    }
//get all payments that client has done
    @GetMapping("getAllPayment")
    public List<PaymentDto>getAll1(){
        return paymentService.getAll();

    }
    //get payment by memebrship id
    @GetMapping("getPaymentByMemberShipId/{membershipId}")
    public PaymentDto getByMemberShipId(@PathVariable("membershipId")Long membershipId){
        return paymentService.findByMemberShiId(membershipId);
    }
}
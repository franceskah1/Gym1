package com.example.gym1.Service;

import com.example.gym1.Converter.PaymentsToPaymentsDto;
import com.example.gym1.Converter.UserToUserDto;
import com.example.gym1.Dto.PaymentDto;
import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.MemberShip;
import com.example.gym1.Model.Payments;
import com.example.gym1.Model.User;
import com.example.gym1.Repo.MemberShipRepo;
import com.example.gym1.Repo.PaymentRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
   UserRepo userRepo;
    @Autowired
    PaymentsToPaymentsDto paymentsToPaymentsDto;


//membership does payment

    public String doPayment(Payments payments) {
        User userDoPayment = userRepo.findById(payments.getUser().getId()).get();
        Payments payments1 = new Payments();
        payments1.setDateOfPayment(payments1.getDateOfPayment());
        payments1.setUser(userDoPayment);
        payments1.setAmount(payments.getAmount());
        paymentRepo.save(payments1);
        return "Your payment has been successfully processed.!";


    }
    //get a specific payments by id

    public PaymentDto getPaymentById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("This is can't be parsed!");
        }
        return paymentsToPaymentsDto.convert(paymentRepo.findById(parseId).orElseThrow(() -> new NotFoundException("Payment with this id cant be found!")));
    }

    //delete payments by id
    public String deletePayment(Long id) {
        Payments paymentsToDelete = paymentRepo.findById(id).orElseThrow(() -> new NotFoundException("Cant find this payment!"));
        paymentRepo.delete(paymentsToDelete);
        return "Payments deleted success!";

    }

    public List<PaymentDto> getAll() {
        return paymentRepo.findAll().stream().map(payments -> paymentsToPaymentsDto.convert(payments)).collect(Collectors.toList());
    }

//get Payment by user di
    public PaymentDto findByMemberShiId(Long memberShipId){
        return paymentsToPaymentsDto.convert(paymentRepo.findByUserId(memberShipId).orElseThrow(()->new NotFoundException("Member not found for this payment!")));

    }
    //get total amount

}
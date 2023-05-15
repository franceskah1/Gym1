package com.example.gym1.Converter;

import com.example.gym1.Dto.PaymentDto;
import com.example.gym1.Dto.UserDto;
import com.example.gym1.Model.Payments;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentsToPaymentsDto implements Converter<Payments, PaymentDto> {
    private final UserToUserDto userToUserDto;

    public PaymentsToPaymentsDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @Override
    public PaymentDto convert(Payments source) {
        if (source != null) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setDateOfPayment(source.getDateOfPayment());
            paymentDto.setId(source.getId());
            paymentDto.setAmount(source.getAmount());

            if (source.getUser() == null) {
                paymentDto.setUser(null);
            } else {
                paymentDto.setUser(userToUserDto.convert(source.getUser()));
            }
                return paymentDto;


        }
        return null;
    }
}
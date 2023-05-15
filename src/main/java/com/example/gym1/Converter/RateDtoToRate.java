package com.example.gym1.Converter;

import com.example.gym1.Dto.RateDto;
import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.Rate;
import com.example.gym1.Repo.UserRepo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RateDtoToRate implements Converter<RateDto, Rate> {
   private final UserRepo userRepo;

    public RateDtoToRate(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public Rate convert(RateDto source) {
        if(source!=null){
            Rate rate=new Rate();
            rate.setRate(source.getRate());
            rate.setComment(source.getComment());

rate.setUser(userRepo.findById(source.getUserId()).orElseThrow(()->new NotFoundException("User not found!")));
    return rate;
        }
        return null;
    }
}

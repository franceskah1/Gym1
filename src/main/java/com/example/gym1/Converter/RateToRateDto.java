package com.example.gym1.Converter;

import com.example.gym1.Dto.RateDto;
import com.example.gym1.Model.Rate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RateToRateDto implements Converter<Rate, RateDto> {
    private final UserToUserDto userDto;

    public RateToRateDto(UserToUserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public RateDto convert(Rate source) {
if (source!=null){
    RateDto rateDto=new RateDto();
    rateDto.setUserDto(userDto.convert(source.getUser()));
    rateDto.setRate(source.getRate());
    rateDto.setComment(source.getComment());
    return rateDto;
}
        return null;
    }
}

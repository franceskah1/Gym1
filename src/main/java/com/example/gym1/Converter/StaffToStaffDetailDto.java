package com.example.gym1.Converter;

import com.example.gym1.Dto.StaffDetailsDto;
import com.example.gym1.Model.Staff;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StaffToStaffDetailDto implements Converter<Staff, StaffDetailsDto> {

    @Override
    public StaffDetailsDto convert(Staff source) {
        if(source!=null){
            StaffDetailsDto staffDetailsDto=new StaffDetailsDto();
            staffDetailsDto.setFirstName(source.getFirstName());
            staffDetailsDto.setLastName(source.getLastName());
            staffDetailsDto.setJob_title(source.getJob_title());
            return staffDetailsDto;

    }
        return null;
    }
}

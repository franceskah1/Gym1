package com.example.gym1.Converter;

import com.example.gym1.Dto.StaffDto;
import com.example.gym1.Model.Staff;
import com.example.gym1.Utils.GenderUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StaffDtoToStaff implements Converter<StaffDto, Staff> {
    @Override
    public Staff convert(StaffDto source) {
        if (source != null) {
            Staff staff = new Staff();
            staff.setId(source.getId());
            staff.setHiredDate(source.getHiredDate());
            staff.setSalary(source.getSalary());
            staff.setEmail(source.getEmail());
            staff.setAddress(source.getAddress());
            staff.setJob_title(source.getJob_title());
            staff.setFirstName(source.getFirstName());
            staff.setLastName(source.getLastName());
            staff.setPhoneNumber(source.getPhoneNumber());
            staff.setGender(GenderUtils.getGender(source.getGender()));
            return staff;

        }

        return null;
    }
}
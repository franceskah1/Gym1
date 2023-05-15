package com.example.gym1.Converter;

import com.example.gym1.Dto.ClassDto;
import com.example.gym1.Model.Class;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClassToClassDto implements Converter<Class, ClassDto> {
private final StaffToStaffDetailDto staffToStaffDetailDto;

    public ClassToClassDto(StaffToStaffDetailDto staffToStaffDetailDto) {
        this.staffToStaffDetailDto = staffToStaffDetailDto;
    }

    @Override
    public ClassDto convert(Class source) {
      if(source!=null){
          ClassDto classDto=new ClassDto();
          classDto.setId(source.getId());
          classDto.setClassCapacity(source.getClassCapacity());
          classDto.setClassName(source.getClassDescription());
          classDto.setClassDescription(source.getClassDescription());
          classDto.setEnd_date(source.getEnd_date());
          classDto.setStart_date(source.getStart_date());
          classDto.setDayOfWeek(source.getDayOfWeek().name());
          classDto.setUserName(source.getStaff().getFirstName());
          if (source.getStaff() == null) {
              classDto.setStaffDetailsDto(null);
          } else {
             classDto.setStaffDetailsDto(staffToStaffDetailDto.convert(source.getStaff()));
          }
          return classDto;
      }
      return null;
    }
}

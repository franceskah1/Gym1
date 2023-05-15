package com.example.gym1.Converter;

import com.example.gym1.Dto.HealthStatusDto;
import com.example.gym1.Model.HealthStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HealthStatusToHealthStatusDto implements Converter<HealthStatus, HealthStatusDto> {
    private final UserToUserDto userToUserDto;

    public HealthStatusToHealthStatusDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @Override
    public HealthStatusDto convert(HealthStatus source) {
        if (source!=null){
            HealthStatusDto healthStatusDto=new HealthStatusDto();
            healthStatusDto.setId(source.getId());
            healthStatusDto.setWeight(source.getWeight());
            healthStatusDto.setRemarks(source.getRemarks());
            healthStatusDto.setHeight(source.getHeight());
            healthStatusDto.setBody_fat_percentage(source.getBody_fat_percentage());

            if (source.getUser() == null) {
                healthStatusDto.setUserDto(null);
            } else {
                healthStatusDto.setUserDto(userToUserDto.convert(source.getUser()));
            }
            return healthStatusDto;


        }
        return null;
    }
}

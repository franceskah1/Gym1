package com.example.gym1.Service;

import com.example.gym1.Converter.HealthStatusToHealthStatusDto;
import com.example.gym1.Dto.HealthStatusDto;
import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.HealthStatus;
import com.example.gym1.Model.User;
import com.example.gym1.Repo.HealthStatusRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthStatusService {
    @Autowired
    HealthStatusRepo healthStatusRepo;
    @Autowired
    UserRepo userRepo;
    private final HealthStatusToHealthStatusDto healthStatusToHealthStatusDto;

    public HealthStatusService(HealthStatusToHealthStatusDto healthStatusToHealthStatusDto) {
        this.healthStatusToHealthStatusDto = healthStatusToHealthStatusDto;
    }

    //USER ADD HEALTH STATUS
    public String addHealthStatus(HealthStatus healthStatus){
    HealthStatus healthStatus1=new HealthStatus();
    User user=userRepo.findById(healthStatus.getUser().getId()).orElseThrow(()->new NotFoundException("Not found User with this id:"));
    healthStatus1.setUser(user);
    healthStatus1.setWeight(healthStatus.getWeight());
    healthStatus1.setHeight(healthStatus.getHeight());
    healthStatus1.setBody_fat_percentage(healthStatus.getBody_fat_percentage());
    healthStatus1.setRemarks(healthStatus.getRemarks());
    healthStatusRepo.save(healthStatus1);
    return "HealthStatus added success!";
}


//get health
public HealthStatusDto getHealthStatus(String id){
        long parseId;

try {
    parseId=Long.parseLong(id);
}catch (NumberFormatException e){
    throw new NotFoundException("THis id can't be parsed!");
}
return healthStatusToHealthStatusDto.convert(healthStatusRepo.findById(parseId).orElseThrow(()->new NotFoundException("Not Found!")));
}
//getHealthStatus by user id

    public HealthStatusDto getHealthStatusById(Long userId){
        return healthStatusToHealthStatusDto.convert(healthStatusRepo.findByUserId(userId).orElseThrow(()->new NotFoundException("Not found")));
    }
}

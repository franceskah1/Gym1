package com.example.gym1.Service;

import com.example.gym1.Converter.AttendanceToAttendaceDto;
import com.example.gym1.Dto.AttendanceDto;
import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.Attendance;
import com.example.gym1.Model.Class;
import com.example.gym1.Model.User;
import com.example.gym1.Repo.AttendanceRepo;
import com.example.gym1.Repo.ClassRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    @Autowired
    AttendanceRepo attendanceRepo;
    private  final UserRepo userRepo;
    private  final ClassRepo classRepo;
private final AttendanceToAttendaceDto attendanceToAttendaceDto;

    public AttendanceService(UserRepo userRepo, ClassRepo classRepo, AttendanceToAttendaceDto attendanceToAttendaceDto) {
        this.userRepo = userRepo;
        this.classRepo = classRepo;
        this.attendanceToAttendaceDto = attendanceToAttendaceDto;
    }

    public String addAttendance(Attendance attendance){
        Attendance attendance1=new Attendance();
        User user=userRepo.findById(attendance.getUser().getId()).orElseThrow(()->new NotFoundException("User with this id does not found!"));
        Class class1=classRepo.findById(attendance.getAClass().getId()).orElseThrow(()->new NotFoundException("Class with this id does not found!"));
        attendance1.setCheck_in(attendance.getCheck_in());
        attendance1.setCheck_out(attendance.getCheck_in());
        attendance1.setId(attendance.getId());
        attendance1.setUser(user);
        attendance1.setAClass(class1);
        attendanceRepo.save(attendance1);
        return "Attendance added success!";
    }


public AttendanceDto getAttendanceById(String id){
        long parseId;
try{
    parseId=Long.parseLong(id);
}catch (NumberFormatException numberFormatException){
    throw new NumberFormatException("This id:"+ id + "can't be parsed!");

}
return attendanceToAttendaceDto.convert(attendanceRepo.findById(parseId).orElseThrow(()->new NotFoundException("Attendance with id can't be parsed!")));
}

//det all atendance
    public List<AttendanceDto>getAll(){
        return attendanceRepo.findAll().stream().map(attendanceToAttendaceDto::convert).collect(Collectors.toList());
    }

    //delete atendance by id

    public void deleteAttendanceByID(Long id) {
        Attendance attendanceToDelete = attendanceRepo.findById(id).orElseThrow(() -> new NotFoundException("This Attendance can't be found!"));
        attendanceRepo.delete(attendanceToDelete);
    }
}
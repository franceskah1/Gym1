package com.example.gym1.Converter;

import com.example.gym1.Dto.AttendanceDto;
import com.example.gym1.Model.Attendance;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttendanceToAttendaceDto implements Converter<Attendance, AttendanceDto> {
    private final UserToUserDto userToUserDto;

    public AttendanceToAttendaceDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @Override
    public AttendanceDto convert(Attendance source) {
        if (source != null) {
            AttendanceDto attendanceDto = new AttendanceDto();
           // attendanceDto.setAttendance_date(source.getAttendance_date());
            attendanceDto.setId(source.getId());
            if (source.getUser() == null) {
                attendanceDto.setUserDto(null);
            } else {
                attendanceDto.setUserDto(userToUserDto.convert(source.getUser()));
            }
            attendanceDto.setClassName(source.getAClass().getClassName()
            );
            return attendanceDto;
        }
        return null;
    }
}
package com.example.gym1.Converter;

import com.example.gym1.Dto.NotificationDto;
import com.example.gym1.Model.NotifyAdmin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotifyAdminToNotifyAdminDto implements Converter<NotifyAdmin, NotificationDto> {


    @Override
    public NotificationDto convert(NotifyAdmin source) {
        if (source != null) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setNotifyAdminId(source.getId());
            notificationDto.setEvent(source.getEvent());
            notificationDto.setDuration_months(source.getDuration_months());
            notificationDto.setStart_date(source.getStart_date());
            notificationDto.setEnd_date(source.getEnd_date());
            notificationDto.setMemberShipType(source.getMemberShipType());
            notificationDto.setLocalDateTime(LocalDateTime.now());
            notificationDto.setUserName(source.getMemberShip().getUser().getUsername());

            return notificationDto;
        }
        return null;
    }
}
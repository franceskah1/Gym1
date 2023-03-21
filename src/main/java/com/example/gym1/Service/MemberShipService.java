package com.example.gym1.Service;

import com.example.gym1.Converter.NotifyAdminToNotifyAdminDto;
import com.example.gym1.Dto.NotificationDto;
import com.example.gym1.Model.MemberShip;
import com.example.gym1.Model.NotifyAdmin;
import com.example.gym1.Model.User;
import com.example.gym1.Repo.MemberShipRepo;
import com.example.gym1.Repo.NotifyRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MemberShipService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    MemberShipRepo memberShipRepo;
    @Autowired
    NotifyRepo notifyRepo;
    @Autowired
    NotifyAdminToNotifyAdminDto notifyAdminToNotifyAdminDto;

    //when user register a membership
    public ResponseEntity<?> createMemberShip(MemberShip memberShip) {
        User user = userRepo.findById(memberShip.getUser().getId()).get();
        MemberShip memberShip1 = new MemberShip();

        memberShip1.setDuration_months(memberShip.getDuration_months());
        memberShip1.setStart_date(memberShip.getStart_date());
        memberShip1.setEnd_date(memberShip.getEnd_date());
        memberShip1.setPrice(memberShip.getPrice());
        memberShip1.setMemberShipType(memberShip.getMemberShipType());
        memberShip1.setUser(user);
        memberShipRepo.save(memberShip1);
        return new ResponseEntity<>("Register Success!", HttpStatus.OK);
    }

    //Update MemberShip
    public ResponseEntity<?> updateMemberShipType(Long id, MemberShip memberShip) {
        String event = "updated";
        if (memberShipRepo.existsById(id)) {
            MemberShip memberShip1 = memberShipRepo.findById(id).get();
            memberShip1.setMemberShipType(memberShip.getMemberShipType());
            memberShip1.setDuration_months(memberShip.getDuration_months());
            memberShip1.setStart_date(memberShip.getStart_date());
            memberShip1.setEnd_date(memberShip.getEnd_date());
            notifyAdmin(memberShip, id, event);
            memberShipRepo.save(memberShip1);

            return new ResponseEntity<>("Data updated success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This membership does not found!", HttpStatus.BAD_REQUEST);
        }

    }

    //create Notification
    private NotifyAdmin notifyAdmin(MemberShip memberShip, Long id, String event) {
        NotifyAdmin notifyAdmin1 = new NotifyAdmin();
        notifyAdmin1.setEvent(event);
        notifyAdmin1.setLt(LocalDateTime.now());
        notifyAdmin1.setDuration_months(memberShip.getDuration_months());
        notifyAdmin1.setMemberShipType(memberShip.getMemberShipType());
        notifyAdmin1.setEnd_date(memberShip.getEnd_date());
        notifyAdmin1.setStart_date(memberShip.getStart_date());
        MemberShip memberShip1 = memberShipRepo.findById(id).get();
        notifyAdmin1.setMemberShip(memberShip1);
        notifyRepo.save(notifyAdmin1);
        return notifyAdmin1;

    }

    //Admin get All notification about user requesting to change membership
    public List<NotificationDto> getAllNotification() {
        return notifyRepo.findAll().stream().map(notifyAdmin -> notifyAdminToNotifyAdminDto.convert(notifyAdmin)).collect(Collectors.toList());
    }

    public NotificationDto getNotificationById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("This id can't be parsed!");
        }
        return notifyAdminToNotifyAdminDto.convert(notifyRepo.findById(parseId).get());

    }

    //delete membership notify admin when a user deleteMembership
    public ResponseEntity<?> deleteMemberShipByid(Long id) {
        String event = "Deleted MemberShip!";
        if (memberShipRepo.existsById(id)) {
            MemberShip memberShip = memberShipRepo.findById(id).get();
            notifyAdmin(memberShip,id,event);
            memberShipRepo.delete(memberShip);

            return new ResponseEntity<>("MemberShip deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Dont have any memberShip to delete!", HttpStatus.BAD_REQUEST);
        }
    }
}
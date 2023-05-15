package com.example.gym1.Service;

import com.example.gym1.Converter.NotifyAdminToNotifyAdminDto;
import com.example.gym1.Dto.NotificationDto;
import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.GymPlan;
import com.example.gym1.Model.MemberShip;
import com.example.gym1.Model.NotifyAdmin;
import com.example.gym1.Model.User;
import com.example.gym1.Repo.MemberShipRepo;
import com.example.gym1.Repo.NotifyRepo;
import com.example.gym1.Repo.PlanRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    PlanRepo planRepo;
    @Autowired
    NotifyAdminToNotifyAdminDto notifyAdminToNotifyAdminDto;

    //when user register a membership
        public ResponseEntity<?> createMemberShip(MemberShip memberShip) {
        String event="New memberShip";
        User user = userRepo.findById(memberShip.getUser().getId()).get();
        GymPlan gymPlan=planRepo.findById(memberShip.getGymPlan().getId()).get();
        MemberShip memberShip1 = new MemberShip();
        memberShip1.setId(memberShip.getId());
        memberShip1.setEnd_date(memberShip.getEnd_date());
        memberShip1.setStart_date(memberShip.getStart_date());
        memberShip1.setMemberShipType(memberShip.getMemberShipType());
        memberShip1.setUser(user);
        memberShip1.setGymPlan(gymPlan);
        memberShipRepo.save(memberShip1);
        createNotification1(memberShip1,event);
        return new ResponseEntity<>("Register Success!", HttpStatus.OK);
    }

    //Update MemberShip
    public ResponseEntity<?> updateMemberShipType(Long id, MemberShip memberShip) {
            String event = "updated";


        if (memberShipRepo.existsById(id)) {
            MemberShip memberShip1 = memberShipRepo.findById(id).get();
            memberShip1.setMemberShipType(memberShip.getMemberShipType());
            memberShip1.setStart_date(memberShip.getStart_date());
          memberShip1.setEnd_date(memberShip.getEnd_date());
            notifyAdmin(memberShip, id, event);
            memberShipRepo.save(memberShip1);

            return new ResponseEntity<>("Data updated success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This membership does not found!", HttpStatus.BAD_REQUEST);
        }

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

        MemberShip memberShip = memberShipRepo.findById(id).orElseThrow(()->new NotFoundException("MemberShip with this id:"+ id + "does not found!"));

        String event = "Deleted MemberShip!";

        memberShipRepo.delete(memberShip);

        createNotificationForDelete(id,memberShip,event);

        return new ResponseEntity<>("MemberShip deleted Success!", HttpStatus.BAD_REQUEST);
        }

    //create Notification for update
    private NotifyAdmin notifyAdmin(MemberShip memberShip, Long id, String event) {
        NotifyAdmin notifyAdmin1 = new NotifyAdmin();
        notifyAdmin1.setEvent(event);
        notifyAdmin1.setLt(LocalDateTime.now());
        notifyAdmin1.setMemberShipType(memberShip.getMemberShipType());
        notifyAdmin1.setEnd_date(memberShip.getEnd_date());
        notifyAdmin1.setStart_date(memberShip.getStart_date());
        MemberShip memberShip1 = memberShipRepo.findById(id).get();
        notifyAdmin1.setMemberShip(memberShip1);
        notifyRepo.save(notifyAdmin1);
        return notifyAdmin1;

    }
    //admin will be notified for new membership
    public NotifyAdmin createNotification1(MemberShip memberShip,String event){
        NotifyAdmin notifyAdmin=new NotifyAdmin();
        MemberShip memberShip1 = memberShipRepo.findById(memberShip.getId()).get();
        //notifyAdmin.setId(memberShip.getId());
        notifyAdmin.setMemberShipType(memberShip.getMemberShipType());
        notifyAdmin.setEvent(event);
        notifyAdmin.setLt(LocalDateTime.now());
        notifyAdmin.setMemberShipType(memberShip.getMemberShipType());
        notifyAdmin.setEnd_date(memberShip.getEnd_date());
        notifyAdmin.setStart_date(memberShip.getStart_date());
        notifyAdmin.setMemberShip(memberShip1);
        notifyRepo.save(notifyAdmin);
        return notifyAdmin;
    }

    //create Notification when user delete Member ship
    //TODO MEMBERSHIP ID NULL
        public NotifyAdmin createNotificationForDelete(Long id,MemberShip memberShip,String event){
        NotifyAdmin deleteMemberShip=new NotifyAdmin();
        MemberShip memberShip1 = memberShipRepo.findById(memberShip.getId()).get();
        deleteMemberShip.setMemberShip(memberShip1);
        deleteMemberShip.setEvent(event);
        deleteMemberShip.setMemberShipType(memberShip.getMemberShipType());
        deleteMemberShip.setDuration_months(deleteMemberShip.getDuration_months());
        deleteMemberShip.setStart_date(memberShip.getStart_date());
        deleteMemberShip.setEnd_date(memberShip.getEnd_date());
        deleteMemberShip.setLt(LocalDateTime.now());
        notifyRepo.save(deleteMemberShip);
        return deleteMemberShip;
    }
}
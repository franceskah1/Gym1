package com.example.gym1.Service;
import com.example.gym1.Converter.StaffDtoToStaff;
import com.example.gym1.Dto.StaffDto;
import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.Staff;
import com.example.gym1.Repo.StaffRepo;
import com.example.gym1.Utils.GenderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    StaffRepo staffRepo;
    private final StaffDtoToStaff toStaff;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PasswordEncoder passwordEncoder;

    public StaffService(StaffDtoToStaff toStaff) {
        this.toStaff = toStaff;
    }

    public ResponseEntity<?> addStaff(StaffDto staff) {
        Staff tranier1 = new Staff();
        if (staffRepo.findByEmail(staff.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
        }else {
            tranier1.setEmail(staff.getEmail());
            tranier1.setFirstName(staff.getFirstName());
            tranier1.setLastName(staff.getLastName());
            tranier1.setSalary(staff.getSalary());
            tranier1.setHiredDate(staff.getHiredDate());
            tranier1.setPhoneNumber(staff.getPhoneNumber());
            tranier1.setJob_title(staff.getJob_title());
            tranier1.setAddress(staff.getAddress());


            tranier1.setGender(GenderUtils.getGender(staff.getGender()));
            staffRepo.save(tranier1);
            return new ResponseEntity<>("Staff register success!", HttpStatus.OK);
        }

    }
public void addStaff1(StaffDto staffDto){
        Staff staffToAdd=toStaff.convert(staffDto);
    assert staffToAdd != null;
    staffRepo.save(staffToAdd);

}
//get Staf by id

    public Staff getById(String id) {
        long parseId;

        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("This id can't be parsed!");

        }
    return staffRepo.findById(parseId).orElseThrow(() -> new NotFoundException("Staff does not found!"));

    }

    //delete By id
    public ResponseEntity<?> deleteStaffById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("This id can't be parsed!");
        }
        staffRepo.deleteById(parseId);
        return new ResponseEntity<>("Staff deleted success!", HttpStatus.OK);
    }

    public String delete(Long id) {
        Staff staffToBeDeleted = staffRepo.findById(id).orElseThrow(() -> new NotFoundException("Staff with this id not found!"));
        staffRepo.delete(staffToBeDeleted);
        return "Staff deleted Success!";
    }

    //get all staff
    public List<Staff> getAll() {
        return staffRepo.findAll();
    }

    //admin update data for staff
    public ResponseEntity<?> updateStaff(Long id, StaffDto staff) {
        if (staffRepo.existsById(id)) {
            Staff staffToUpdate = staffRepo.findById(id).orElseThrow(() -> new NotFoundException("Staff with this id does not found!"));
            staffToUpdate.setJob_title(staffToUpdate.getJob_title());
            staffToUpdate.setSalary(staffToUpdate.getSalary());
            staffToUpdate.setPhoneNumber(staffToUpdate.getPhoneNumber());
            staffToUpdate.setEmail(staffToUpdate.getEmail());
            staffToUpdate.setGender(GenderUtils.getGender(staff.getGender()));
            staffToUpdate.setAddress(staff.getAddress());
            staffToUpdate.setFirstName(staff.getFirstName());
            staffRepo.save(staffToUpdate);
            return new ResponseEntity<>("Staff updated!", HttpStatus.OK);
        }
        return null;
    }
}
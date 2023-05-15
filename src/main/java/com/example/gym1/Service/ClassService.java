package com.example.gym1.Service;

import com.example.gym1.Converter.ClassToClassDto;
import com.example.gym1.Dto.ClassDto;
import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.Class;
import com.example.gym1.Model.Staff;
import com.example.gym1.Repo.ClassRepo;
import com.example.gym1.Repo.StaffRepo;
import com.example.gym1.Utils.DayOfWeekUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {
    private final StaffRepo staffRepo;
    private final ClassRepo classRepo;
    @Autowired
    ClassToClassDto classToClassDto;

    public ClassService(StaffRepo staffRepo, ClassRepo classRepo) {
        this.staffRepo = staffRepo;
        this.classRepo = classRepo;
    }

//create a new class,choose staff
    public String createClass(ClassDto class1){
        Class class2=new Class();
        Staff staffToAdd=staffRepo.findById(class1.getStaff().getId()).orElseThrow(()->new NotFoundException( "Staff Not Found!"));
        class2.setStaff(staffToAdd);
        class2.setId(class1.getId());
        if(class1.getClassCapacity()>=100){
            return "please choose another class.";

        }else {
            class2.setClassCapacity(class1.getClassCapacity());
        }
        class2.setClassName(class1.getClassName());
        class2.setEnd_date(class1.getStart_date());
        class2.setDayOfWeek(DayOfWeekUtils.getDayOfWeek(class1.getDayOfWeek()));
        class2.setStart_date(class1.getStart_date());
        class2.setClassDescription(class1.getClassDescription());
        classRepo.save(class2);
        return "Class saved success!";
    }
    //get class by id
 public ClassDto getClassById(String id){
        long parseId;
    try{
        parseId=Long.parseLong(id);
    }catch (NumberFormatException e){
        throw new NumberFormatException("Class id: \"" + id + "\" can't be parsed!");
    }
    return classToClassDto.convert(classRepo.findById(parseId).orElseThrow(()->new NotFoundException("Record with id: " + id + " not found!")));

    }
    //delete class by Id
    public ResponseEntity<?> deleteClassById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
            classRepo.deleteById(parseId);
            return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK);

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Class id: \"" + id + "\" can't be parsed!", HttpStatus.BAD_REQUEST);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Record with id " + id + " not found", HttpStatus.BAD_REQUEST);
            // throw new NotFoundException("Record with id: " + id + " not found!");
        } catch (Exception e) {
            return new ResponseEntity<>("Record could not be deleted", HttpStatus.BAD_REQUEST);
        }
    }
    //get All Class
    public List<ClassDto> getAll(){
      return classRepo.findAll().stream().map(aClass -> classToClassDto.convert(aClass)).collect(Collectors.toList());
    }
       }
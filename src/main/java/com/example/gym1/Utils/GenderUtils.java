package com.example.gym1.Utils;

import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.Gender;

public class GenderUtils {
    public static Gender getGender(String gender) {

        if (gender.equalsIgnoreCase(Gender.Male.name())) {
            return Gender.Male;

        } else if (gender.equalsIgnoreCase(Gender.Female.name())) {
            return Gender.Female;

        } else {
            throw new NotFoundException("Gender not found! ");
        }
    }

}

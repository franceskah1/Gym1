package com.example.gym1.Utils;

import com.example.gym1.Exceptions.NotFoundException;
import com.example.gym1.Model.DayOfWeek;
import com.example.gym1.Model.Gender;

public class DayOfWeekUtils {

    public static DayOfWeek getDayOfWeek(String dayOfWeek) {

        if (dayOfWeek.equalsIgnoreCase(DayOfWeek.MONDAY.name())) {
            return DayOfWeek.MONDAY;

        } else if (dayOfWeek.equalsIgnoreCase(DayOfWeek.THURSDAY.name())) {
            return DayOfWeek.TUESDAY;

        } else if (dayOfWeek.equalsIgnoreCase(DayOfWeek.TUESDAY.name())) {
            return DayOfWeek.TUESDAY;

        } else if (dayOfWeek.equalsIgnoreCase(DayOfWeek.WEDNESDAY.name())) {
            return DayOfWeek.WEDNESDAY;

        } else if (dayOfWeek.equalsIgnoreCase(DayOfWeek.FRIDAY.name())) {
            return DayOfWeek.FRIDAY;

        } else if (dayOfWeek.equalsIgnoreCase(DayOfWeek.SATURDAY.name())) {
            return DayOfWeek.SATURDAY;

        } else if (dayOfWeek.equalsIgnoreCase(DayOfWeek.SUNDAY.name())) {
            return DayOfWeek.SUNDAY;

        } else {
            throw new NotFoundException("Day not found! ");
        }
    }
}

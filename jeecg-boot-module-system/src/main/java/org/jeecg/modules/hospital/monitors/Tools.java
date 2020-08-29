package org.jeecg.modules.hospital.monitors;

import java.util.Calendar;
import java.util.Date;

public abstract class Tools {
    public static int currDay(Date startdate, Date enddate) {
        if (enddate == null) {
            enddate = new Date();
        }
        return differentDays(startdate, enddate);
    }

    public static int differentDays(Date startdate, Date enddate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startdate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(enddate);

        int day1 = cal1.get(6);
        int day2 = cal2.get(6);

        int year1 = cal1.get(1);
        int year2 = cal2.get(1);

        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0)) {
                    timeDistance += 366;
                } else {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1) + 1;
        }
        return day2 - day1 + 1;
    }
}

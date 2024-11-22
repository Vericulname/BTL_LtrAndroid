package com.example.clockapp.placeholder;

import com.example.clockapp.fragments.alarmFragment;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class alarm implements Serializable {

    private Calendar calendar;
    private Map<Integer,String> days;
    private final String alarm_song;
    private boolean status;

    public alarm(  String alarm_song, Map<Integer,String> days,Calendar calendar)  {

        this.days = days;
        this.status = status;
        this.alarm_song = alarm_song;
        this.calendar = calendar;

    }


    public Calendar getCalendar() {

        return calendar;

    }

    public Map<Integer,String> getDays() {
        return days;
    }



    public boolean isStatus() {
        return status;
    }

}

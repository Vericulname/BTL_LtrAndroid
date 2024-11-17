package com.example.clockapp.placeholder;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class alarm implements Serializable {

    private Calendar calendar;
    private Map<Integer,String> days;
    private Time time;
    private final String alarm_song;
    private boolean status;

    public alarm(  String alarm_song, Map<Integer,String> days,Time time, boolean status)  {

        this.days = days;
        this.status = status;
        this.alarm_song = alarm_song;
        this.time = time;

    }

    public Time getTime() {
        return time;
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

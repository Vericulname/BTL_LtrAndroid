package com.example.clockapp.placeholder;

import com.example.clockapp.fragments.alarmFragment;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class alarm implements Serializable {

    private Long time;
    private ArrayList<Integer> days;
    private final String alarm_song;
    private boolean status;

    public alarm(  String alarm_song, ArrayList<Integer> days,Long time)  {
        this.days = days;
        this.alarm_song = alarm_song;
        this.time = time;

    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDays(ArrayList<Integer> days) {
        this.days = days;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getTime() {

        return time;

    }
    public String getAlarm_song() {
        return alarm_song;
    }

    public ArrayList<Integer> getDays() {
        return days;
    }

    public boolean isStatus() {
        return status;
    }

}

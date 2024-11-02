package com.example.clockapp.placeholder;

import android.icu.util.Calendar;
import android.location.Location;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class clock implements Serializable  {
    Locale location;
    TimeZone timeZone;
    public clock(TimeZone timeZone, Locale location) {
        this.timeZone = timeZone;
        this.location = location;
    }
    public Locale getLocation() {
        return location;
    }

    public void setLocation(Locale location) {
        this.location = location;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}

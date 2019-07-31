package com.hearkensummertask.hearkensummertask.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GetNowTime {
    public String getTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        return dateFormat.format(date);
    }

}

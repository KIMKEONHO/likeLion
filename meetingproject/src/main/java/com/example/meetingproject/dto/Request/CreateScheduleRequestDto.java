package com.example.meetingproject.dto.Request;

import com.example.meetingproject.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter@Setter
public class CreateScheduleRequestDto {
    private String title;
    private String description;
    private LocalDate date;
    private String time;
    private String location;

    public Schedule toEntity(){
        Schedule schedule = new Schedule();
        schedule.setTitle(title);
        schedule.setDescription(description);
        schedule.setMeetingDate(date);
        schedule.setMeetingTime(convertToLocalTime(time));
        schedule.setLocation(location);
        return schedule;
    }

    private LocalTime convertToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")); // "HH:mm" 포맷으로 변환
    }
}

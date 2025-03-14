package com.example.meetingproject.dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class SearchScheduleDto {
    private Long id;
    private String title;
    private String location;
    private LocalDate date;
    private String time;
}

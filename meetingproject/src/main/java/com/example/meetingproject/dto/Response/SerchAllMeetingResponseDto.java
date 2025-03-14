package com.example.meetingproject.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SerchAllMeetingResponseDto {
    private Long id;
    private String title;
    private String description;
    private Integer maxParticipants;
    private Integer currentParticipants;
}

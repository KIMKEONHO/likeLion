package com.example.meetingproject.dto.Request;

import com.example.meetingproject.entity.Meeting;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ModifyMeetingRequestDto {
    private String title;
    private String description;
    private Integer maxParticipants;

}

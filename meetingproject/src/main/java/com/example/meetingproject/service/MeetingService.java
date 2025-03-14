package com.example.meetingproject.service;

import com.example.meetingproject.dto.Response.SerchAllMeetingResponseDto;
import com.example.meetingproject.entity.Meeting;
import com.example.meetingproject.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public void saveMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    public List<SerchAllMeetingResponseDto> getAllMeetings() {
        List<Meeting> meetings = meetingRepository.findAll();

        return meetings.stream().map(meeting -> {
            SerchAllMeetingResponseDto dto = new SerchAllMeetingResponseDto();
            dto.setId(meeting.getId());
            dto.setTitle(meeting.getTitle());
            dto.setDescription(meeting.getDescription());
            dto.setMaxParticipants(meeting.getMaxParticipants());

            int currentParticipants = meeting.getMeetingparticipants() != null ? meeting.getMeetingparticipants().size() : 0;
            dto.setCurrentParticipants(currentParticipants);

            return dto;
        }).collect(Collectors.toList());
    }

    public void deleteMeeting(Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }

    public Meeting findById(Long meetingId) {
        return meetingRepository.findById(meetingId).orElse(null);
    }
}

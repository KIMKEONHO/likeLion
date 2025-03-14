package com.example.meetingproject.service;

import com.example.meetingproject.dto.Response.ParticipantsResponseDto;
import com.example.meetingproject.entity.MeetingParticipant;
import com.example.meetingproject.entity.User;
import com.example.meetingproject.repository.MeetingParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MeetingParticipantService {
    private final MeetingParticipantRepository meetingParticipantRepository;

    public void save(MeetingParticipant meetingParticipant) {
        meetingParticipantRepository.save(meetingParticipant);
    }

    public List<ParticipantsResponseDto> findByMeetingId(Long meetingId) {
        List<MeetingParticipant> participants = meetingParticipantRepository.findByMeetingId(meetingId);

        return participants.stream().map(participant -> {
            User user = participant.getUser();
            ParticipantsResponseDto dto = new ParticipantsResponseDto();
            dto.setId(user.getId());
            dto.setName(user.getUsername()); // 사용자 이름을 설정
            return dto;
        }).collect(Collectors.toList());
    }


}

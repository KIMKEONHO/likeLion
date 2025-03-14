package com.example.meetingproject.service;

import com.example.meetingproject.dto.Response.ScheduleParticipantDto;
import com.example.meetingproject.dto.Response.SearchScheduleDto;
import com.example.meetingproject.entity.Schedule;
import com.example.meetingproject.entity.ScheduleParticipant;
import com.example.meetingproject.entity.User;
import com.example.meetingproject.repository.ScheduleParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleParticipantService {
    private final ScheduleParticipantRepository scheduleParticipantRepository;

    public void joinScheduleParticipant(User user, Schedule schedule) {
        ScheduleParticipant scheduleParticipant = new ScheduleParticipant();
        scheduleParticipant.setUser(user);
        scheduleParticipant.setSchedule(schedule);
        scheduleParticipantRepository.save(scheduleParticipant);
    }

    public List<ScheduleParticipantDto> getScheduleParticipants(Long meetingId, Long schduleId) {
        List<ScheduleParticipant> participants = scheduleParticipantRepository.findByUser_IdAndSchedule_Id(meetingId, schduleId);

        return participants.stream().map(participant -> {
            ScheduleParticipantDto dto = new ScheduleParticipantDto();

            dto.setId(participant.getUser().getId());
            dto.setName(participant.getUser().getUsername());

            return dto;
        }).collect(Collectors.toList());
    }
}

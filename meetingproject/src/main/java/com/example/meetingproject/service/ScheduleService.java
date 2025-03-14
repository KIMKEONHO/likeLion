package com.example.meetingproject.service;

import com.example.meetingproject.dto.Response.SearchScheduleDto;
import com.example.meetingproject.dto.Response.SerchAllMeetingResponseDto;
import com.example.meetingproject.entity.Meeting;
import com.example.meetingproject.entity.Schedule;
import com.example.meetingproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public void save(Schedule schedule){
        scheduleRepository.save(schedule);
    }

    public List<SearchScheduleDto> getAllMeetings(Long meetingId) {
        List<Schedule> schedules = scheduleRepository.findByMeetingId(meetingId);

        return schedules.stream().map(schedule -> {
            SearchScheduleDto dto = new SearchScheduleDto();
            dto.setId(schedule.getId());
            dto.setTitle(schedule.getTitle());
            dto.setDate(schedule.getMeetingDate());
            dto.setTime(String.valueOf(schedule.getMeetingTime()));
            dto.setLocation(schedule.getLocation());

            return dto;
        }).collect(Collectors.toList());
    }

    public Schedule findById(Long meetingId) {
        return scheduleRepository.findById(meetingId).orElse(null);
    }

    public void delete(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}

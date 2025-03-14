package com.example.meetingproject.repository;

import com.example.meetingproject.entity.Schedule;
import com.example.meetingproject.entity.ScheduleParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleParticipantRepository extends JpaRepository<ScheduleParticipant, Long> {
    List<ScheduleParticipant> findByUser_IdAndSchedule_Id(Long userId, Long scheduleId);
}

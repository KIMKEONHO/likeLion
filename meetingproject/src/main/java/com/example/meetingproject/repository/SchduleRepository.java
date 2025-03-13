package com.example.meetingproject.repository;

import com.example.meetingproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchduleRepository extends JpaRepository<Schedule, Long> {
}

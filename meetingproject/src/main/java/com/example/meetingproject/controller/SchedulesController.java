package com.example.meetingproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class SchedulesController {

    @PostMapping("/{meetings}/schedules")
    public ResponseEntity<String> cretaeChedule(@PathVariable("meetings") String meetings) {

        // 일정 생성 로직
        return ResponseEntity.ok("meetings : " + meetings);
    }

    @PostMapping("/{meetingId}/schedules/{scheduleId}/join")
    public ResponseEntity<String> joinSchedukes(@PathVariable("meetingId") String meetingId, @PathVariable("scheduleId") String scheduleId) {

        // 일정 참가 로직
        return ResponseEntity.ok("join schedules");
    }

    @DeleteMapping("/{meetingId}/schedules/{scheduleId}/leave")
    public ResponseEntity<String> deleteSchedule(@PathVariable("meetingId") String meetingId, @PathVariable("scheduleId") String scheduleId) {

        // 일정 탈퇴 로직
        return ResponseEntity.ok("delete schedule");
    }

}

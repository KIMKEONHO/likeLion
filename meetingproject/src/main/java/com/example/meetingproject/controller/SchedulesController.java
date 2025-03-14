package com.example.meetingproject.controller;

import com.example.meetingproject.dto.Request.CreateScheduleRequestDto;
import com.example.meetingproject.dto.Response.ScheduleParticipantDto;
import com.example.meetingproject.dto.Response.SearchScheduleDto;
import com.example.meetingproject.entity.Schedule;
import com.example.meetingproject.entity.ScheduleParticipant;
import com.example.meetingproject.entity.User;
import com.example.meetingproject.service.MeetingService;
import com.example.meetingproject.service.ScheduleParticipantService;
import com.example.meetingproject.service.ScheduleService;
import com.example.meetingproject.service.UserService;
import com.example.meetingproject.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
@Tag(name = "스케쥴 관리 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class SchedulesController {

    private final JwtUtil jwtUtil;
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final MeetingService meetingService;
    private final ScheduleParticipantService scheduleParticipantService;

    @PostMapping("/{meetingId}/schedules")
    @Operation(
            summary = "스케쥴 생성 기능",
            description = "token을 통해 인증 후 인증이 됐다면 입력받은 dto를 통해 정보를 가공후 DB에 저장하는 메서드",
            tags = "스케쥴 관리 컨트롤러"
    )
    public ResponseEntity<String> cretaeChedule(@RequestHeader("Authorization") String token, @PathVariable("meetingId") Long meetingId, @RequestBody CreateScheduleRequestDto requestDto) {

        String jwtToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        log.info("JWT token: " + jwtToken);

        Long userId = jwtUtil.validateToken(jwtToken);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Schedule schedule = requestDto.toEntity();
        schedule.setCreatedBy(userService.findById(userId).orElseThrow(()->new EntityNotFoundException("엔티티를 찾을 수 없습니다.")));
        schedule.setMeeting(meetingService.findById(meetingId));

        scheduleService.save(schedule);

        return ResponseEntity.ok("스케쥴 생성 성공 ");
    }

    @PostMapping("/{meetingId}/schedules/{scheduleId}/join")
    @Operation(
            summary = "스케쥴 참여 기능",
            description = "token을 통해 인증 후 인증이 됐다면 정보를 가공하여 DB에 저장하는 메서드",
            tags = "스케쥴 관리 컨트롤러"
    )
    public ResponseEntity<String> joinSchedukes(@RequestHeader("Authorization") String token, @PathVariable("meetingId") Long meetingId, @PathVariable("scheduleId") Long scheduleId) {

        String jwtToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        log.info("JWT token: " + jwtToken);

        Long userId = jwtUtil.validateToken(jwtToken);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Schedule schedule = scheduleService.findById(scheduleId);
        User user = userService.findById(userId).orElseThrow();
        scheduleParticipantService.joinScheduleParticipant(user,schedule);

        // 일정 참가 로직
        return ResponseEntity.ok("join schedules");
    }

    @DeleteMapping("/{meetingId}/schedules/{scheduleId}/leave")
    @Operation(
            summary = "스케쥴 삭제 기능",
            description = "scheduleId를 통해 스케쥴 참가자를 DB에서 제거하는 메서드",
            tags = "스케쥴 관리 컨트롤러"
    )
    public ResponseEntity<String> deleteSchedule(@RequestHeader("Authorization") String token, @PathVariable("meetingId") Long meetingId, @PathVariable("scheduleId") Long scheduleId) {

        String jwtToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        log.info("JWT token: " + jwtToken);

        Long userId = jwtUtil.validateToken(jwtToken);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        scheduleService.delete(scheduleId);

        // 일정 탈퇴 로직
        return ResponseEntity.ok("delete schedule");
    }

    @GetMapping("/{meetingId}/schedules")
    @Operation(
            summary = "스케쥴 조회 기능",
            description = "meetingId를 통해 스케쥴 정보들을 가져와 사용자에게 제공하는 메서드",
            tags = "스케쥴 관리 컨트롤러"
    )
    public ResponseEntity<?> schedules(@PathVariable("meetingId") Long meetingId) {

        List<SearchScheduleDto> schedules = scheduleService.getAllMeetings(meetingId);

        // 일정 목록 조회
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{meetingId}/scheduels/{scheduleId}/participants")
    @Operation(
            summary = "스케쥴 참여 명단 조회 기능",
            description = "meetingId를 통해 스케쥴 참여자 정보들을 가져와 사용자에게 제공하는 메서드",
            tags = "스케쥴 관리 컨트롤러"
    )
    public ResponseEntity<?> participants(@PathVariable("meetingId") Long meetingId, @PathVariable("scheduleId") Long scheduleId) {

        List<ScheduleParticipantDto> participants = scheduleParticipantService.getScheduleParticipants(meetingId, scheduleId);

        // 특정 일정 참가자 목록 조회
        return ResponseEntity.ok(participants);
    }

}

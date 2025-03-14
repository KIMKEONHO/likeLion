package com.example.meetingproject.controller;

import com.example.meetingproject.dto.Request.CreateMeetingRequestDto;
import com.example.meetingproject.dto.Request.ModifyMeetingRequestDto;
import com.example.meetingproject.dto.Response.ParticipantsResponseDto;
import com.example.meetingproject.dto.Response.SerchAllMeetingResponseDto;
import com.example.meetingproject.entity.Meeting;
import com.example.meetingproject.entity.MeetingParticipant;
import com.example.meetingproject.entity.User;
import com.example.meetingproject.service.MeetingParticipantService;
import com.example.meetingproject.service.MeetingService;
import com.example.meetingproject.service.UserService;
import com.example.meetingproject.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
@Tag(name = "모임 관리 컨트롤러")
@RequiredArgsConstructor
@Slf4j
public class MeetingController {

    private final JwtUtil jwtUtil;
    private final MeetingService meetingService;
    private final UserService userService;
    private final MeetingParticipantService meetingParticipantService;

    @PostMapping("/")
    @Operation(
            summary = "모임 생성 기능",
            description = "token을 통해 인증 후 인증이 됐다면 user 객체를 얻어와 정보를 DB에 저장하는 메서드",
            tags = "모임 관리 컨트롤러"
    )
    public ResponseEntity<String> createMeeting(@RequestHeader("Authorization") String token, @RequestBody CreateMeetingRequestDto meetingRequestDto) {

        String jwtToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        log.info("JWT token: " + jwtToken);

        Long userId = jwtUtil.validateToken(jwtToken);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.findByEmail(meetingRequestDto.getUserEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        log.info(user.toString());

        // 엔티티 변환후 유저 설정
        Meeting meeting = meetingRequestDto.toEntity();
        meeting.setCreater(user);

        meetingService.saveMeeting(meeting);
        return ResponseEntity.ok("모임 생성 성공");
    }

    @GetMapping("/")
    @Operation(
            summary = "모임 조회 기능",
            description = "모든 모임을 읽어들여 알맞게 데이터 가공후 사용자에게 제공하는 메서드",
            tags = "모임 관리 컨트롤러"
    )
    public ResponseEntity<List<SerchAllMeetingResponseDto>> meetings() {

        // 조회 로직
        List<SerchAllMeetingResponseDto> allMeetings = meetingService.getAllMeetings();
        return ResponseEntity.ok(allMeetings);
    }

    @PostMapping("/{meetingId}/join")
    @Operation(
            summary = "모임 참가 기능",
            description = "",
            tags = "모임 관리 컨트롤러"
    )
    public ResponseEntity<?> join(@RequestHeader("Authorization") String token, @PathVariable("meetingId") Long meetingId) {

        String jwtToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        log.info("JWT token: " + jwtToken);

        Long userId = jwtUtil.validateToken(jwtToken);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.findById(userId).orElseThrow(RuntimeException::new);
        Meeting meeting = meetingService.findById(meetingId);

        MeetingParticipant meetingParticipant = new MeetingParticipant();
        meetingParticipant.setMeeting(meeting);
        meetingParticipant.setUser(user);

        meetingParticipantService.save(meetingParticipant);

        // 참가 로직
        return ResponseEntity.ok("모임 참가 성공!");
    }

    @PutMapping("/{meetingId}")
    @Operation(
            summary = "모임 수정 기능",
            description = "토큰 인증 후 받은 dto로 부터 새롭게 Entity를 설정하여 DB에 저장하는 메서드",
            tags = "모임 관리 컨트롤러"
    )
    public ResponseEntity<String> update(@RequestHeader("Authorization") String token, @PathVariable("meetingId") Long meetingId, @RequestBody ModifyMeetingRequestDto meetingRequestDto) {

        String jwtToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        log.info("JWT token: " + jwtToken);

        Long userId = jwtUtil.validateToken(jwtToken);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Meeting meeting = meetingService.findById(meetingId);
        meeting.setDescription(meetingRequestDto.getDescription());
        meeting.setTitle(meetingRequestDto.getTitle());
        meeting.setMaxParticipants(meetingRequestDto.getMaxParticipants());

        meetingService.saveMeeting(meeting);

        // 수정 로직
        return ResponseEntity.ok("meeting updated");
    }

    @DeleteMapping("/{meetingId}")
    @Operation(
            summary = "모임 삭제 기능",
            description = "토큰 인증 후 유저 정보와 미팅 정보를 가져와 비교후 DB에서 삭제하는 메서드",
            tags = "모임 관리 컨트롤러"
    )
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String token, @PathVariable("meetingId") Long meetingId) {

        String jwtToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        log.info("JWT token: " + jwtToken);

        Long userId = jwtUtil.validateToken(jwtToken);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Meeting meeting = meetingService.findById(meetingId);
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        if(meeting.getCreater().equals(user)){
            meetingService.deleteMeeting(meetingId);
            return ResponseEntity.ok("meeting deleted");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    @GetMapping("/{meetingId}/participants")
    @Operation(
            summary = "모임 참가자 목록 조회 기능",
            description = "해당하는 id 값의 모임의 참가자 정보를 사용자에게 제공하는 메서드",
            tags = "모임 관리 컨트롤러"
    )
    public ResponseEntity<?> participants(@PathVariable("meetingId") Long meetingId) {
        List<ParticipantsResponseDto> participantList = meetingParticipantService.findByMeetingId(meetingId);
        return ResponseEntity.ok(participantList);
    }

}

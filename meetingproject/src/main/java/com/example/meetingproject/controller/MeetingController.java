package com.example.meetingproject.controller;

import com.example.meetingproject.entity.Meeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    @PostMapping("/{meetingId}/join")
    public String join(@PathVariable("meetingId") int meetingId) {

        // 참가 로직
        return "join";
    }

    @PutMapping("/{meetingId}")
    public ResponseEntity<String> update(@PathVariable("meetingId") int meetingID, @RequestBody Meeting meeting) {

        // 수정 로직
        return ResponseEntity.ok("meeting updated");
    }

    @DeleteMapping("/{meetingId}")
    public ResponseEntity<String> delete(@PathVariable("meetingId") int meetingID) {

        // 삭제 로직
        return ResponseEntity.ok("meeting deleted");
    }

}

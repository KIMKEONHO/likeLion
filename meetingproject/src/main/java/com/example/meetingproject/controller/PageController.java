package com.example.meetingproject.controller;

import com.example.meetingproject.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "/main/login";
    }

    @GetMapping("/meetings/{meetingId}/participants")
    public String participants(@PathVariable("meetingId") int meetingId) {

        // 모임 참가자 목록 조회
        return "participants";
    }

    @GetMapping("/meetings/{meetingId}/schedules")
    public String schedules(Model model, @PathVariable("meetingId") String meetingId) {

        // 일정 목록 조회
        return "schedules";
    }

    @GetMapping("/meetings/{meetingId}/scheduels/{scheduleId}/participants")
    public String participants(Model model, @PathVariable("meetingId") String meetingId) {

        // 특정 일정 참가자 목록 조회
        return "participants";
    }

    @GetMapping("/meetings")
    public String meetings() {

        // 조회 로직
        return "meetings";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping("/signup")
    public String signup() {
        return "main/signup";
    }
}

package com.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitController {

    @GetMapping("/visit")
    public String visit(@CookieValue(name = "lastVisit", defaultValue = "N/A") String lastVisit,
            HttpServletResponse response, Model model
    ) {

        Cookie cookie = new Cookie("lastVisit", "KEONHO");
        // / 주소에서 쿠키값을 사용 가능하게 설정
//        cookie.setDomain("/");
        // 쿠키의 유지시간(초단위)
        cookie.setMaxAge(60 * 60);

        // qksemtl 응답에 포함 시켜야 함
        response.addCookie(cookie);
       model.addAttribute("lastVisit", lastVisit);
       return "visit";
    }
}

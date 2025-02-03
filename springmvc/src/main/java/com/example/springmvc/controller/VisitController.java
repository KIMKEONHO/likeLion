package com.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitController {

    @GetMapping("/visit")
    public String visit(@CookieValue(name = "lastVisit", defaultValue = "N/A") String lastVisit,
            HttpServletResponse response, Model model, HttpServletRequest request
    ) {

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + ":::" + value);
            }
        }

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

    @GetMapping("/cookieSetForm")
    public String cookieSetForm() {
        // 쿠키 이름과 값을 받을 화면을 리턴
        return "cookieset";
    }

    @GetMapping("/cookieSet")
    public String cookieSet(HttpServletRequest request, HttpServletResponse response) {
        String cookieName = request.getParameter("cookieName");
        String cookieValue = request.getParameter("cookieValue");

        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24); // 1일 동안 유지
        response.addCookie(cookie);

        return "redirect:/cookieView";
    }

    @GetMapping("/cookieView")
    public String cookieView(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        model.addAttribute("cookies", cookies);
        return "cookieview"; // 쿠키를 보여주는 화면
    }
}

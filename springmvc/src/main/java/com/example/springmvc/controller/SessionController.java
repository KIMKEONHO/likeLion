package com.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/session")
// 선언함으로 visitCount에 해당하는 값은 세션에 저장되어 사용됨
@SessionAttributes("visitCount")
public class SessionController {

    @ModelAttribute("visitCount")
    public Integer visitCount() {
        return 0;
    }

    @GetMapping("/visit")
    public String visit(@ModelAttribute("visitCount") Integer visitCount, Model model) {
        visitCount = visitCount + 1;
        model.addAttribute("visitCount", visitCount);
        return "visitSession";
    }

    @GetMapping("/resetVisit")
    public String resetVisit(HttpSession session) {
        session.removeAttribute("visitCount");
        return "redirect:/session/visit";
    }

    @GetMapping("/resetVisit")
    public String resetVisit( SessionStatus status) {
        status.setComplete();  // 세션 자체 삭제.
        return "redirect:/session/visit";
    }

    @GetMapping("/visit")
    public String visit(HttpSession session, Model model){
        //HttpSession 은 HttpServletRequest의 getSession() 메서드로 얻어올 수 있는데,
        //getSession() 이라는 메서드는 세션이 이미 존재하면 있는것을 리턴해주고,
        // 없다면 새로 생성해서 리턴 (세션객체를 생성하고, sessionId 를 발급받아서 쿠키로만들어
        // response에 넣는 작업까지 실행된다.
        Integer visitCount =  (Integer)session.getAttribute("visitCount");
        if(visitCount == null){
            visitCount = 0;
        }
        visitCount++;

        session.setAttribute("visitCount", visitCount);
        model.addAttribute("visitCount",visitCount);
        return "visitSession";
    }

}

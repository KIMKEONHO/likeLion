package com.example.boardproject.controller;

import com.example.boardproject.domain.BoardUser;
import com.example.boardproject.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new BoardUser());
        return "main/login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("user") BoardUser User, RedirectAttributes redirectAttributes) {
        if(loginService.login(User.getUser_id(),User.getUser_password())){
            return "redirect:/board/list";
        }else{
            redirectAttributes.addFlashAttribute("error", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "redirect:/login";
        }
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new BoardUser());
        model.addAttribute("ages", IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList()));
        return "main/signup";
    }

    @PostMapping("/signup")
    public String signupPost(@ModelAttribute("user") @Valid BoardUser user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ages", IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList()));
            return "main/signup";
        }
        if(loginService.signup(user)){
            return "redirect:/login";
        }else{
            return "redirect:/login";
        }
    }

//    @GetMapping("/check-id")
//    public String checkId(@RequestParam String userId) {
//
//    }

}

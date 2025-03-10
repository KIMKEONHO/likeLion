package com.example.oauthexam.controller;

import com.example.oauthexam.dto.SocialUserRequestDto;
import com.example.oauthexam.entity.SocialLoginInfo;
import com.example.oauthexam.security.CustomUserDetails;
import com.example.oauthexam.service.SocialLoginInfoService;
import com.example.oauthexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SocialLoginInfoService socialLoginInfoService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "oauth/welcome";
    }

    @GetMapping("/loginform")
    public String loginForm() {
        return "oauth/users/loginform";
    }

    @GetMapping("/registerSocialUser")
    public String registerSocialUser(@RequestParam("provider") String provider, @RequestParam("socialId") String SocialId, @RequestParam("name") String name, @RequestParam("uuid") String uuid, Model model) {
        model.addAttribute("provider", provider);
        model.addAttribute("SocialId", SocialId);
        model.addAttribute("name", name);
        model.addAttribute("uuid", uuid);

        return "oauth/users/registerSocialUser";
    }

    @PostMapping("/saveSocialUser")
    public String saveSocialUser(@ModelAttribute SocialUserRequestDto socialUserRequestDto) {
        Optional<SocialLoginInfo> socialLoginInfo = socialLoginInfoService.findByProviderAndUuidAndSocialId(socialUserRequestDto.getProvider(), socialUserRequestDto.getUuid(), socialUserRequestDto.getSocialId());

        if (socialLoginInfo.isPresent()) {
            SocialLoginInfo socialLoginInfoEntity = socialLoginInfo.get();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(socialLoginInfoEntity.getCreateAt(), now);
            if(duration.toMinutes() > 20) {
                return "redirect:/error";  // 20분 이상 경과하면 에러 페이지로 리다이렉트
            }

            userService.saveUser(socialUserRequestDto, passwordEncoder);
            return "redirect:/info";
        }else{
            return "redirect:/eroor";
        }
    }

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        model.addAttribute("user", customUserDetails);

        return "oauth/info";
    }


}

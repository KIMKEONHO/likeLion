package com.example.meetingproject.controller;


import com.example.meetingproject.dto.Request.SignUpRequestDto;
import com.example.meetingproject.dto.Response.SignupResponseDto;
import com.example.meetingproject.entity.User;
import com.example.meetingproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "회원 관리 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @Operation(
            summary = "회원 가입 기능",
            description = "유효성 검사 후 인코딩된 비밀번호로 회원가입",
            tags = "회원 관리 컨트롤러"
    )
    public ResponseEntity<String> signup(@Valid @RequestBody SignupResponseDto signupResponseDto, BindingResult bindingResult) {
        signupResponseDto.setPassword(passwordEncoder.encode(signupResponseDto.getPassword()));
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getAllErrors().toString());
            StringBuilder errorMessage = new StringBuilder("유효성 검사 실패: ");
            bindingResult.getFieldErrors().forEach(error ->
                    errorMessage.append(error.getDefaultMessage()).append(" ")
            );
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        userService.save(signupResponseDto.toEntity());
        return ResponseEntity.ok("{\"message\": \"회원가입 성공!\"}");
    }

    @PostMapping("/login")
    @Operation(
            summary = "로그인 기능",
            description = "입력 필드를 기반으로 이메일 중복 확인 후 패스워드 비교",
            tags = "회원 관리 컨트롤러"
    )
    public ResponseEntity<String> login(@RequestBody SignUpRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        if (!userService.isEmailDuplicate(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 잘못된 이메일 또는 비밀번호.");
        }

        User user = userService.findByEmail(email).get();

        if (user == null || !userService.checkPassword(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 잘못된 이메일 또는 비밀번호.");
        }

        return ResponseEntity.ok("로그인 성공: " + email);
    }

    @PostMapping("/logout")
    @Operation(
            summary = "로그아웃 기능",
            description = "로그인되어 있는 회원 정보 반납",
            tags = "회원 관리 컨트롤러"
    )
    public ResponseEntity<String> logout() {

        // 로그아웃 로직
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PostMapping("/check-email/")
    @Operation(
            summary = "이메일 중복 체크 기능",
            description = "이메일 중복 체크 기능",
            tags = "회원 관리 컨트롤러"
    )
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);

        if (isDuplicate) {
            // 이메일이 중복된 경우
            return ResponseEntity.badRequest().body("{\"isDuplicate\": true, \"message\": \"이미 사용 중인 이메일입니다.\"}");
        } else {
            // 이메일이 사용 가능할 경우
            return ResponseEntity.ok("{\"isDuplicate\": false, \"message\": \"사용 가능한 이메일입니다.\"}");
        }

    }
}

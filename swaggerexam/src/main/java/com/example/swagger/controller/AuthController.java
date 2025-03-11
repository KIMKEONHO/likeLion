package com.example.swagger.controller;

import com.example.swagger.dto.LoginRequestDto;
import com.example.swagger.dto.RegisterRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "인증 관련 API")
public class AuthController {
    @Operation(
            summary = "회원가입",
            description = "이메일과 비밀번호를 입력하여 회원가입을 합니다.",
            tags = {"Authentication"}
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok("success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto){
        return ResponseEntity.ok("success");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Parameter(description = "JWT 인증 토큰", required = true, example = "Bearer e")
            @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.ok("success");
    }
}

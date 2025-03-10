package com.example.oauthexam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialUserRequestDto {
    @NotBlank(message = "provider은 필수 입력값입니다.")
    private String provider;
    private String socialId;
    private String name;
    private String email;
    private String username;
    private String uuid;
}

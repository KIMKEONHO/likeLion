package com.example.meetingproject.dto.Response;

import com.example.meetingproject.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
public class SignupResponseDto {
    @NotEmpty(message = "이름은 공백을 허용하지 않습니다.")
    @Size(max = 20, message = "username 최대 20자 까지만 허용합니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 공백을 허용하지 않습니다.")
    @Pattern(
            regexp = ".*[!@#$%^&*(),.?\":{}|<>].*",
            message = "비밀번호는 최소 하나의 특수문자를 포함해야 합니다."
    )
    @Size(min = 6, message = "비밀번호는 최소 6자 이상 입력해야 합니다.")
    private String password;

    @NotEmpty(message = "이메일은 공백을 허용하지 않습니다.")
    private String email;

    public User toEntity(){
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}

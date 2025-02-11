package com.example.boardproject.domain;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUser {

    @Id
    private Long id;
    @NotEmpty(message = "id는 공백을 허용하지 않습니다.")
    @Size(min = 3, max = 10, message = "user_id는 4~10자 까지만 허용합니다.")
    private String user_id;

    @NotEmpty(message = "username 은 공백을 허용하지 않습니다.")
    @Size(max = 20, message = "username 최대 20자 까지만 허용합니다.")
    private String user_name;

    @NotEmpty(message = "password 은 공백을 허용하지 않습니다.")
    @Pattern(
            regexp = ".*[!@#$%^&*(),.?\":{}|<>].*",
            message = "비밀번호는 최소 하나의 특수문자를 포함해야 합니다."
    )
    @Size(min = 6, message = "비밀번호는 최소 6자 이상 입력해야 합니다.")
    private String user_password;

    @NotEmpty(message = "주소는 공백을 허용하지 않습니다.")
    private String address;

    @Min(value = 1, message = "나이는 1세 이상이어야 합니다.")
    @Max(value = 100, message = "나이는 100세 이하이어야 합니다.")
    private Integer age;

}

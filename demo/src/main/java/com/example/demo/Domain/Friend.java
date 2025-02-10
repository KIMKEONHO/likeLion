package com.example.demo.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Friend {
    @Id
    private Long id;
    private String name;
    private String email;
}

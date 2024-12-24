package day12;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person implements Serializable {
    private String name;
    private int age;


}

package day05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Person {
    String name;
    String address;
    int age;
    boolean isVip;
    static int count = 0;

}

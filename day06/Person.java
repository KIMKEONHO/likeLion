package day06;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String name;
    private int age;
    private String phoneNumber;

    public Person(String nmae) {
        this.name = nmae;
    }

    public Person(String name, int age, String phoneNumber) {
        this(name);
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}

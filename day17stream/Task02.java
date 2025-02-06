package day17stream;

import java.util.Arrays;
import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Alice"), new Person("Bob"), new Person("Charlie"));
        double av = people.stream()
                            .mapToInt(p -> p.getNome().length())
                            .average()
                            .orElse(0.0);
    }
}

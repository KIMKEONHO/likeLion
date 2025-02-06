package day17stream;

import java.util.Arrays;
import java.util.List;

public class StreamExam04 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,-5);

        System.out.println(numbers.stream().allMatch(n -> n > 0));
        boolean anyNegative = numbers.stream().anyMatch(n -> n < 0);
        System.out.println(anyNegative);

    }
}

package day17stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Task01 {
    public static void main(String[] args) {
        List<Integer> nubers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Predicate<Integer> isEven = n -> n % 2 == 0;

        boolean even = nubers.stream().allMatch(n -> n % 2 == 0);
        System.out.println(even);
    }
}

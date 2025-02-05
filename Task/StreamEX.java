package Task;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEX {

    // 1. 스트림 만들기

    // 배열 스트림
    String[] arr = new String[]{"a", "b", "c"};
    Stream<String> stream = Arrays.stream(arr);

    // 컬렉션 스트림
    List<String> list = Arrays.asList("a","b","c");
    Stream<String> stream2 = list.stream();

    // 빌더 사용
    Stream<String> builderStream = Stream.<String>builder()
            .add("a").add("b").add("c")
            .build();

    // 람다식 generate, iterate
    Stream<String> generatedStream = Stream.generate(()->"a").limit(3);
    Stream<Integer> iteratedStream = Stream.iterate(0, n->n+2).limit(5); //0,2,4,6,8

    // 기본 타입형 스트림
    IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]

    // 병렬 스트림 parallelStream
    Stream<String> parallelStream = list.parallelStream();


    // 2. 중간 연산

    // Filtering
    List<String> list2 = Arrays.asList("a","b","c");
    Stream<String> stream3 = list.stream()
            .filter(list -> list.contains("a"));

    // Mapping
    Stream<String> stream4 = list.stream()
            .map(String::toUpperCase);
    //[A,B,C]

//    .map(Integers::parseInt);
//    // 문자열 -> 정수로 변환


    // Sorting
    Stream<String> stream5 = list.stream()
            .sorted() // [a,b,c] 오름차순 정렬
            .sorted(Comparator.reverseOrder()); // [c,b,a] (내림차순)

    List<String> list3 = Arrays.asList("a","bb","ccc");
    Stream<String> stream6 = list.stream()
            .sorted(Comparator.comparingInt(String::length)); // [ccc,bb,a] //문자열 길이 기준 정렬


    // 최종 연산

    // Calculating
//    IntStream stream = list.stream()
//            .count()   //스트림 요소 개수 반환
//            .sum()     //스트림 요소의 합 반환
//            .min()     //스트림의 최소값 반환
//            .max()     //스트림의 최대값 반환
//            .average();//스트림의 평균값 반환

    // Reduction
//    IntStream stream = IntStream.range(1,5)
//	.reduce(10, (total,num)->total+num);

    //예시 리스트
//    List<Person> members = Arrays.asList(new Person("lee",26),
//            new Person("kim", 23),
//            new Person("park", 23));

    // toList() - 리스트로 반환
//    members.stream()
//            .map(Person::getLastName)
//            .collect(Collectors.toList());

    // 외에 joining, groupingBy, collectingAndThen등이 있음

    // Matching
//    List<String> members = Arrays.asList("Lee", "Park", "Hwang");
//    boolean matchResult = members.stream()
//            .anyMatch(members->members.contains("w")); //w를 포함하는 요소가 있는지, True
//        // 외에 allMatch, noneMatch 등이 있음
//
//    // Iterating
//    members.stream()
//            .map(Person::getName)
//    .forEach(System.out::println);

    // Finding
//    Person person = members.stream()
//            .findAny()   //먼저 찾은 요소 하나 반환, 병렬 스트림의 경우 첫번째 요소가 보장되지 않음
//            .findFirst() //첫번째 요소 반환

}



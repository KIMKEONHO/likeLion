package day17stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamExam05 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/day17stream/");
        Stream<Path> stream = Files.list(path);
        stream.forEach(p-> System.out.println(p.getFileName().toString()));
        stream.close();

        Stream<String> stream1 = Files.lines(Paths.get("src/day17stream/StreamExam01.java"));
        stream1.forEach(System.out::println);

    }
}

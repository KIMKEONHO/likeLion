package day12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class IOExam03 {
    public static void main(String[] args) throws Exception {

        URL url = new URL("https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html");

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        String line = null;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}

package day12;

import java.io.*;

public class IOExam02 {
    public static void main(String[] args) throws IOException {

        InputStream is = System.in;

        // 파일에서 입력
        FileInputStream fls = new FileInputStream("input.txt");

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("입력하세요 : ");
        String s = br.readLine();

        System.out.println(s);
    }
}

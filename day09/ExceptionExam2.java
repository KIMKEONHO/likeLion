package day09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionExam2 {

    // 예외를 직접처리하는 방식
    public static void methodA(){
        try {
            FileInputStream fis = new FileInputStream("abc");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 예외를 던져주는 방식
    public static void methodB() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("abc");
    }

    public static void main(String[] args) {
        methodA();
        try {
            methodB();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

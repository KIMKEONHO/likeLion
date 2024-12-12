package day09;

import java.io.FileInputStream;

//예외 종류가 2가지
//실행시 발생하는 예외 (Runtime Exception)
//컴파일시 체크하는 예외 (checked Exception)
public class ExceptionExam01 {
    public static void main(String[] args) {
        int[] iarr = {1,2,3,4};
        iarr[5] = 10;  // 컴파일시에 체크하지 않는다

        FileInputStream fis = null;
//        fis = new FileInputStream();
    }
}

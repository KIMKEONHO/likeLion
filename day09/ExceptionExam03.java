package day09;

import java.io.File;
import java.io.FileInputStream;

public class ExceptionExam03 {
    public static void main(String[] args) {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream("abc");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }



        // 위와 같ㅇ이 사용하던 것을 try-with-resources 라는 구문이 추가됨으로서 좀더 편하게 사용가능
        try (FileInputStream fils2 = new FileInputStream("abc")){

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

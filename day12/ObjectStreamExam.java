package day12;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamExam {
    public static void main(String[] args) {
        // 객체를 파일에 저장하려면 객체를 직렬화 해야한다.
        Person kim = new Person("kim",25);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Person.text"));
            out.writeObject(kim);
        }catch (Exception e){

        }
    }
}

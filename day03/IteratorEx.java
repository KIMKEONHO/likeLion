package day03;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorEx {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");

        // 이터레이터 클래스 생성
        Iterator<String> iterator = list.iterator();

        // 요소 반복
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);

            // 요소 제거
            if (next.equals("A")) {
                iterator.remove();
            }
        }
    }
}

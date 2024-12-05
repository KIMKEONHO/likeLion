package day04;

public class Exam1 {
    public static void main(String[] args) {
        int i = 10;

        int j = i;

        i += 10;

        int []iarr = {10};
        int []iarr2 = iarr;

        iarr[0] += 10;

        // 래퍼런스 변수는 주소를 저장하기에 iarr값이 바뀌게 되면 iarr2의 값도 변경된다.
        System.out.println("iarr : " + iarr[0]);
        System.out.println("iarr2 : " + iarr2[0]);
    }
}

package day03;

import java.util.Scanner;

public class Gugudan {
    public static void main(String[] args) {
        System.out.print("원하는 단을 입력하세요 : ");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        for(int i = 1; i <= 9; i++) {
            System.out.println(a + " * " + i + " = " + a * i);
        }
    }
}

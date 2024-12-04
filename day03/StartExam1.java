package day03;

import java.util.Scanner;

public class StartExam1 {
    public static void main(String[] args) {
        System.out.println("삼각형 별의 층을 입력해주세요.");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        for(int i = 1; i < a + 1; i++){
            for(int j = 0; j < i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

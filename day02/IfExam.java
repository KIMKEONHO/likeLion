package day02;

import java.util.Scanner;

public class IfExam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score;
        System.out.println("Enter your score: ");
        score = sc.nextInt();

        if (score >= 90) {
            System.out.println("A 학점");
        }else if (score >= 80) {
            System.out.println("B 학점");
        }else if (score >= 70) {
            System.out.println("C 학점");
        }else if (score >= 60) {
            System.out.println("D 학점");
        }else {
            System.out.println("F 학점");
        }
    }
}

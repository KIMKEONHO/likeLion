package backendproject;

import BankProject.src.main.java.com.example.demo.Util.MenuOption;
import lombok.Builder;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Builder
public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=======사자 은행 시스템=======");
        System.out.println("1. 로그인");
        System.out.println("2. 고객등록");
        System.out.println("3. 관리자 로그인");
        System.out.println("4. 종료");
        System.out.println("===========================");

        int i;
        while (true) {
            try {
                i = scanner.nextInt();
                if (i == 1) {
                    MenuOption.handleLogin(scanner);
                } else if (i == 2) {
                    MenuOption.handleCustomerRegistration(scanner);
                } else if (i == 3) {
                    // 관리자 로그인
                } else if (i == 4) {
                    System.out.println("시스템을 종료합니다.");
                    return;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("정수를 입력해야 합니다. 다시 시도하세요.");
                scanner.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("=======사자 은행 시스템=======");
            System.out.println("1. 로그인");
            System.out.println("2. 고객등록");
            System.out.println("3. 관리자 로그인");
            System.out.println("4. 종료");
            System.out.println("===========================");
        }

    }


}

package com.example.demo.Util;

import com.example.demo.Exception.AccountNotFoundException;
import com.example.demo.Exception.BankOperationException;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.BankService;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.LoginManager;
import lombok.Getter;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.example.demo.Util.AccountUtil.allAccountDisplay;


@Getter
public enum MenuOption {

    CREATE_ACCOUNT(1){
        @Override
        public void executeMenuOption(Scanner scanner) throws SQLException, AccountNotFoundException {
            System.out.println("계좌 생성을 선택하셨습니다.");

            BankService bank = BankUtil.returnInputBank(scanner);

            customerService.createAccount(bank.createAccountNumber());
        }
    },
    DEPOSIT(2){
        @Override
        public void executeMenuOption(Scanner scanner) throws AccountNotFoundException {
            System.out.println("입금을 선택하셨습니다.");

            allAccountDisplay();

            System.out.print("입금을 원하는 계좌를 입력해주세요 : ");
            String banknumber = scanner.next();

            System.out.print("원하시는 금액을 입력해주세요 : ");
            int money = scanner.nextInt();
            accountService.deposit(banknumber,money);
        }
    },
    WITHDRAW(3){
        @Override
        public void executeMenuOption(Scanner scanner) throws AccountNotFoundException {
            System.out.println("출금을 선택하셨습니다.");

            allAccountDisplay();

            System.out.print("입금을 원하는 계좌를 입력해주세요 : ");
            String banknumber = scanner.next();

            System.out.print("원하시는 금액을 입력해주세요 : ");
            int money = scanner.nextInt();
            accountService.withdraw(banknumber, money);
        }
    },
    VIEW_BALANCE(4){
        @Override
        public void executeMenuOption(Scanner scanner) throws AccountNotFoundException {
            System.out.println("현재 고객님의 모든 계좌의 잔액을 조회합니다.");
            accountService.serchAccount();
        }
    },
    EXIT(5){
        @Override
        public void executeMenuOption(Scanner scanner) {
            System.out.println("로그아웃합니다.");
            loginManager.logout();
        }
    }
    ;

    private static CustomerService customerService = new CustomerService();
    private static AccountService accountService = new AccountService();
    private final int option;
    private static LoginManager loginManager = LoginManager.getInstance();

    MenuOption(int option) {
        this.option = option;
    }

    public static MenuOption fromOption(int option)throws BankOperationException {
        for(MenuOption menuOption : values()){
            if(menuOption.getOption() == option){
                return menuOption;
            }
        }
        throw new BankOperationException("잘못된 번호를 입력하셨습니다. 다시 선택하세요");
    }

    public static void handleLogin(Scanner scanner) throws SQLException {
        System.out.print("아이디를 입력하세요: ");
        String inputId = scanner.next();
        System.out.print("비밀번호를 입력하세요: ");
        String inputPW = scanner.next();

        LoginManager loginManager = LoginManager.getInstance();
        boolean isLoginSuccessful = loginManager.login(inputId, inputPW);

        if (isLoginSuccessful) {
            System.out.println("로그인 성공!");
            String loggedInUser = loginManager.getLoggedInUserId();
            System.out.println("현재 로그인한 사용자: " + loggedInUser);

            int actionChoice;
            do {
                actionChoice = displayMenu(scanner);
                handleMenuOption(actionChoice, scanner);
            } while (actionChoice != MenuOption.EXIT.getOption());
        } else {
            System.out.println("ID 혹은 PW가 다릅니다.");
        }
    }

    private static int displayMenu(Scanner scanner) {
        System.out.println("1. 계좌 생성");
        System.out.println("2. 입금");
        System.out.println("3. 출금");
        System.out.println("4. 잔액 조회");
        System.out.println("5. 로그아웃");

        int actionChoice = -1;
        try {
            actionChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("정수를 입력해야 합니다. 다시 시도하세요.");
            scanner.next(); // 잘못된 입력 버리기
        }
        return actionChoice;
    }

    private static void handleMenuOption(int actionChoice, Scanner scanner) {
        try {
            MenuOption menuOption = MenuOption.fromOption(actionChoice);
            menuOption.executeMenuOption(scanner);
        } catch (IllegalArgumentException e) {
            System.out.println("유효하지 않은 메뉴 선택입니다. 다시 시도하세요.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void handleCustomerRegistration(Scanner scanner) throws SQLException {
        System.out.println("고객 등록을 선택하셨습니다.");

        System.out.print("고객의 이름을 입력하세요 : ");
        String name = scanner.next();

        System.out.print("고객의 id를 입력하세요 : ");
        String id = scanner.next();

        System.out.print("고객의 PW를 입력하세요 : ");
        String password = scanner.next();

        BankService bank = BankUtil.returnInputBank(scanner);

//        bank.saveCustomer(name, id, password, bankId);
    }

    public abstract void executeMenuOption(Scanner scanner) throws SQLException, AccountNotFoundException;
}

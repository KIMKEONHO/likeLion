package backendproject.Util;

import backendproject.Dto.AccountDto;
import backendproject.Service.AccountService;
import backendproject.Service.NhBankService;
import backendproject.Service.BankService;
import backendproject.Dto.CustomerDto;
import backendproject.Exception.AccountNotFoundException;
import backendproject.Exception.BankOperationException;
import backendproject.Service.LoginManager;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static backendproject.Util.AccountUtil.allAccountDisplay;

@Getter
public enum MenuOption {

    CREATE_ACCOUNT(1){
        @Override
        public void executeMenuOption(Scanner scanner) throws SQLException {
            System.out.println("계좌 생성을 선택하셨습니다.");

            BankService bank = null;

            BankUtil bankUtil = new BankUtil();

            bankUtil.showAllBank();

            System.out.print("생성하고자 하는 은행 입력하세요: ");
            int i = scanner.nextInt();

            switch (i){
                case 1:
                    bank = new NhBankService();
                    // ...  기타 은행들 //
            }
            assert bank != null;
            accountService.createAccount(bank.createAccountNumber());
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
        public void executeMenuOption(Scanner scanner) {

        }
    },
    VIEW_BALANCE(4){
        @Override
        public void executeMenuOption(Scanner scanner) {

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

        BankUtil bankUtil = new BankUtil();
        bankUtil.showAllBank();
        System.out.print("원하시는 은행을 선택하세요(정수로) : ");
        int bankId = scanner.nextInt();

//        bank.saveCustomer(name, id, password, bankId);
    }

    public abstract void executeMenuOption(Scanner scanner) throws SQLException, AccountNotFoundException;
}

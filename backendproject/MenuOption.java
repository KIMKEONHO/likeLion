package backendproject;

import backendproject.Exception.BankOperationException;
import lombok.Getter;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Getter
public enum MenuOption {

    CREATE_ACCOUNT(1){
        @Override
        public void executeMenuOption(Scanner scanner, Bank bank){
            System.out.println("계좌 생성을 선택하셨습니다.");

            try {
                bank.showAllBank();
            }catch (SQLException e){
                e.printStackTrace();
            }

            System.out.print("생성하고자 하는 은행 입력하세요: ");
            int i = scanner.nextInt();

            switch (i){
                case 1:
                    bank = new NhBank();
                    // ...  기타 은행들 //
            }

            Customer customer = new Customer();
            customer.createAccount(bank.createAccountNumber());
        }
    },
    DEPOSIT(2){
        @Override
        public void executeMenuOption(Scanner scanner, Bank bank) {

        }
    },
    WITHDRAW(3){
        @Override
        public void executeMenuOption(Scanner scanner, Bank bank) {

        }
    },
    VIEW_BALANCE(4){
        @Override
        public void executeMenuOption(Scanner scanner, Bank bank) {

        }
    };

    private final int option;

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

    public static void handleLogin(Scanner scanner, Bank bank) throws SQLException {
        System.out.print("아이디를 입력하세요 : ");
        String inputId = scanner.next();
        System.out.print("비밀번호를 입력하세요 : ");
        String inputPW = scanner.next();

        LoginManager loginManager = LoginManager.getInstance();
        boolean isLoginSuccessful = loginManager.login(inputId, inputPW);

        if(isLoginSuccessful) {
            System.out.println("로그인 성공!");
            String loggedInUser = loginManager.getLoggedInUserId();
            System.out.println("현재 로그인한 사용자: " + loggedInUser);

            int actionChoice = 0;
            do {
                System.out.println("1. 계좌 생성");
                System.out.println("2. 입금");
                System.out.println("3. 출금");
                System.out.println("4. 잔액 조회");
                System.out.println("5. 종료");

                try {
                    actionChoice = scanner.nextInt();
                    MenuOption menuOption = MenuOption.fromOption(actionChoice);
                    menuOption.executeMenuOption(scanner, bank);
                } catch (InputMismatchException e) {
                    System.out.println("정수를 입력해야 합니다. 다시 시도하세요.");
                    scanner.next();
                } catch (BankOperationException e) {
                    System.out.println(e.getMessage());
                }
            } while (actionChoice != 5);
        }else{
            System.out.println("ID 혹은 PW가 다릅니다.");
        }

    }

    public static void handleCustomerRegistration(Scanner scanner, Bank bank) throws SQLException {
        System.out.println("고객 등록을 선택하셨습니다.");

        System.out.print("고객의 이름을 입력하세요 : ");
        String name = scanner.next();

        System.out.print("고객의 id를 입력하세요 : ");
        String id = scanner.next();

        System.out.print("고객의 PW를 입력하세요 : ");
        String password = scanner.next();

        bank.showAllBank();
        System.out.print("원하시는 은행을 선택하세요(정수로) : ");
        int bankId = scanner.nextInt();

        bank.saveCustomer(name, id, password, bankId);
    }

    public abstract void executeMenuOption(Scanner scanner, Bank bank);
}

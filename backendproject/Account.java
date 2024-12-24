package backendproject;

import backendproject.Exception.AccountNotFoundException;
import backendproject.Exception.InvalidTransactionException;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    // id값
    private int id;

    // 계좌 번호
    private String bankNumber;

    // 잔액
    private int balance;

    // 출금
    public void outMoney(Customer customer, String bankNumber, int money) throws AccountNotFoundException {
        // 계좌 있는지 체크 로직
        customer.isIn(bankNumber);

        try {
            if (money <= 0) {
                throw new InvalidTransactionException("출금 금액은 0보다 커야 합니다.");
            }

            if (money > balance) {
                throw new InvalidTransactionException("출금 금액이 계좌 잔액보다 많을 수 없습니다.");
            }

            // 계좌 가져오기 로직
            Account account = null;
            for (Account acc : customer.getAccounts()) {
                if (acc.getBankNumber().equals(bankNumber)) {
                    account = acc;
                    break;
                }
            }

            account.setBalance(account.getBalance() - money);
            System.out.println("출금이 완료되었습니다. 현재 잔액: " + account.getBalance());

        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // 입금
    public void inMoney(Customer customer, String bankNumber, int money) throws AccountNotFoundException {

        // 계좌 있는지 체크 로직
        customer.isIn(bankNumber);

        try {
            if (money <= 0) {
                throw new InvalidTransactionException("입금 금액은 0보다 커야 합니다.");
            }

            // 계좌 가져오기 로직
            Account account = null;
            for (Account acc : customer.getAccounts()) {
                if (acc.getBankNumber().equals(bankNumber)) {
                    account = acc;
                    break;
                }
            }

            account.setBalance(account.getBalance() + money);
            System.out.println("입금이 완료되었습니다. 현재 잔액: " + account.getBalance());

        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // 잔액 조회
    public void serchAccount(Customer customer,String bankNumber) throws AccountNotFoundException {
        // 계좌 있는지 체크 로직
        customer.isIn(bankNumber);

        System.out.println(bankNumber + "의 현재 잔액 : " + customer.findAccount(bankNumber).getBalance());

    }


}

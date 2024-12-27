package backendproject.Service;

import backendproject.Dto.AccountDto;
import backendproject.Dto.CustomerDto;
import backendproject.Exception.AccountNotFoundException;
import backendproject.Exception.InvalidTransactionException;
import backendproject.Repository.AccountRepository;
import backendproject.Repository.DatabaseConnect;

import java.util.ArrayList;

public class AccountService {

    private AccountDto accountDto;
    private CustomerDto customerDto;
    private DatabaseConnect db = DatabaseConnect.getInstance();
    private AccountRepository aRepo = new AccountRepository();

    // 출금
    public void outMoney(CustomerDto customer, String bankNumber, int money) throws AccountNotFoundException {
        // 계좌 있는지 체크 로직
        if(isin(bankNumber)){
            try {
                if (money <= 0) {
                    throw new InvalidTransactionException("출금 금액은 0보다 커야 합니다.");
                }

                // 계좌 가져오기 로직
                AccountDto account = null;
                for (AccountDto acc : customer.getAccounts()) {
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
        }else {
            throw new AccountNotFoundException("계좌가 없습니다.");
        }

    }

    // 입금
    public void deposit(String bankNumber, int money) throws AccountNotFoundException {

        if(isin(bankNumber)){
            try {
                if (money <= 0) {
                    throw new InvalidTransactionException("입금 금액은 0보다 커야 합니다.");
                }

                for(AccountDto account : LoginManager.getInstance().getAccounts()){
                    if (account.getBankNumber().equals(bankNumber)) {
                        account.setBalance(account.getBalance() + money);
                        aRepo.deposit(account.getBalance(), bankNumber);
                        System.out.println("입금 성공 !");
                        System.out.println("현재 " + bankNumber + "계좌의 잔액 : " + account.getBalance());
                    }
                }
                System.out.println("다음 작업을 선택하세요.");
                System.out.println("===================================");

            } catch (Exception e) {
                System.out.println("예외 발생: " + e.getMessage());
            }
        }else{
            throw new AccountNotFoundException("해당 계좌가 없습니다.");
        }
    }

    // 잔액 조회
    public void serchAccount(CustomerDto customer, String bankNumber) throws AccountNotFoundException {
        // 계좌 있는지 체크 로직
        isin(bankNumber);

    }

    public boolean isin(String bankNumber){
        ArrayList<AccountDto> accounts = LoginManager.getInstance().getAccounts();
        return accounts.stream().anyMatch(account -> account.getBankNumber().equals(bankNumber));
    }

    public void createAccount(String bankbumber){
        aRepo.createAccount(bankbumber);
    }
}

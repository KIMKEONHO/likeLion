package backendproject.Service;

import backendproject.Dto.AccountDto;
import backendproject.Dto.CustomerDto;
import backendproject.Exception.AccountNotFoundException;
import backendproject.Exception.InvalidTransactionException;
import backendproject.Repository.AccountRepository;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;

public class AccountService {

    private AccountRepository aRepo = new AccountRepository();

    // 출금
    public void withdraw(String bankNumber, int money) throws AccountNotFoundException {
        // 계좌 있는지 체크 로직
        if(isin(bankNumber)){
            try {
                if (money <= 0) {
                    throw new InvalidTransactionException("출금 금액은 0보다 커야 합니다.");
                }
                for(AccountDto account : LoginManager.getInstance().getAccounts()){
                    if (account.getBankNumber().equals(bankNumber)) {
                        if(account.getBalance() >= money){
                            account.setBalance(account.getBalance() - money);
                            aRepo.withdraw(account.getBalance(), bankNumber);
                            System.out.println("출금 성공 !");
                            System.out.println("현재 " + bankNumber + "계좌의 잔액 : " + account.getBalance());
                        }else{
                            throw new AccountNotFoundException("출금 금액이 잔고 보다 큽니다!");
                        }
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
    public void serchAccount() throws AccountNotFoundException {
        ArrayList<AccountDto> accounts = null;
        accounts = LoginManager.getInstance().getAccounts();
        System.out.println("===========================");
        if(accounts.size() > 0){
            int i = 1;
            for(AccountDto account : accounts){
                System.out.println(i + ". " + account.getBankNumber() + "의 잔액 : " + account.getBalance());
                i ++ ;
            }
        }
        System.out.println("===========================");

    }

    public boolean isin(String bankNumber){
        ArrayList<AccountDto> accounts = LoginManager.getInstance().getAccounts();
        return accounts.stream().anyMatch(account -> account.getBankNumber().equals(bankNumber));
    }

    public void createAccount(String bankbumber){
        aRepo.createAccount(bankbumber);
        System.out.println("계좌 번호 : " + bankbumber + " 생성 완료!");
        System.out.println("다음 작업을 진행해주세요.");
        System.out.println("==============================");
    }
}

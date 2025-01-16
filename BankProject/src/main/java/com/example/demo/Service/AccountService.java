package com.example.demo.Service;

import com.example.demo.Dto.AccountDto;
import com.example.demo.Exception.AccountNotFoundException;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Util.AccountUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountService {

    private AccountRepository aRepo = new AccountRepository();

    // 출금
    public void withdraw(String bankNumber, int money) throws AccountNotFoundException {
        // 계좌 있는지 체크 로직
        AccountDto account = findAccountByBankNumber(bankNumber);
        if (account != null) {
            if (money <= 0) {
                throw new AccountNotFoundException("출금 금액은 0보다 커야 합니다.");
            }
            if (account.getBalance() <= money) {
                throw new AccountNotFoundException("출금 금액이 잔고 보다 큽니다!");
            }

            account.setBalance(account.getBalance() - money);
            aRepo.updateBalance(account.getBalance(), bankNumber);
            System.out.println("출금 성공 !");
            AccountUtil.displayAccountChangeSuccess(account);
        } else {
            throw new AccountNotFoundException("해당 계좌가 없습니다.");
        }
    }

    // 입금
    public void deposit(String bankNumber, int money) throws AccountNotFoundException {
        // 계좌 있는지 체크 로직
        AccountDto account = findAccountByBankNumber(bankNumber);
        if (account != null) {
            if (money <= 0) {
                throw new AccountNotFoundException("입금 금액은 0보다 커야 합니다.");
            }

            account.setBalance(account.getBalance() + money);
            aRepo.updateBalance(account.getBalance(), bankNumber);
            System.out.println("입금 성공 !");
            AccountUtil.displayAccountChangeSuccess(account);
        } else {
            throw new AccountNotFoundException("해당 계좌가 없습니다.");
        }
    }

    // 잔액 조회
    public void serchAccount() {
        ArrayList<AccountDto> accounts = LoginManager.getInstance().getAccounts();
        System.out.println("===========================");
        if (!accounts.isEmpty()) {
            int i = 1;
            for (AccountDto account : accounts) {
                System.out.println(i + ". " + account.getBankNumber() + "의 잔액 : " + account.getBalance());
                i++;
            }
        }
        System.out.println("===========================");
    }

    // 계좌번호로 account 찾기
    public static AccountDto findAccountByBankNumber(String bankNumber){
        for(AccountDto accountDto : LoginManager.getInstance().getAccounts()){
            if(accountDto.getBankNumber().equals(bankNumber)){
                return accountDto;
            }
        }
        return null;
    }

}

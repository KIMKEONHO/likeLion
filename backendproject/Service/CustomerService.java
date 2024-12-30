package backendproject.Service;

import backendproject.Dto.AccountDto;
import backendproject.Dto.CustomerDto;
import backendproject.Exception.AccountNotFoundException;
import backendproject.Repository.AccountRepository;

import java.util.ArrayList;

import static backendproject.Util.AccountUtil.allAccountDisplay;

public class CustomerService {

    AccountRepository accountRepo = new AccountRepository();

    // 계좌 중복 체크
    public Boolean isIn(String bankNumber) throws AccountNotFoundException {
        boolean found = false;

        for (AccountDto account : LoginManager.getInstance().getAccounts()) {
            System.out.println(account.getBankNumber());
            if (bankNumber.equals(account.getBankNumber())) {
                found = true;
                return found;
            }
        }

        return found;
    }

    // 계좌 생성
    public void createAccount(String bankNumber) {
        try {
            // 빈 계좌인지 체크
            ArrayList<AccountDto> accounts = LoginManager.getInstance().getAccounts();
            if(accounts != null) {
                // 게좌가 5개 이상인지 체크
                if (accounts.size() >= CustomerDto.ACC_MAX_SIZE) {
                    throw new AccountNotFoundException("이미 5개의 계좌가 있습니다.");
                }
                // 중복된 계좌인지 체크
                if (isIn(bankNumber)) {
                    throw new AccountNotFoundException("이미 있는 계좌입니다.");
                }

                accountRepo.createAccount(bankNumber);

            }else {
                accountRepo.createAccount(bankNumber);
            }

            // 계좌 목록 출력
            allAccountDisplay();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // 계좌 찾기
    public AccountDto findAccount(String banknumber) throws AccountNotFoundException {
        AccountDto result = null;

        return null;
    }
}

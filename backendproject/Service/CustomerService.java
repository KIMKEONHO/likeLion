package backendproject.Service;

import backendproject.Dto.AccountDto;
import backendproject.Dto.CustomerDto;
import backendproject.Exception.AccountNotFoundException;
import backendproject.Repository.AccountRepository;

public class CustomerService {

    AccountRepository accountRepo = new AccountRepository();

    public void createAccount(String bankbumber) throws AccountNotFoundException {
        if(checkMaxAccount()){
            accountRepo.createAccount(bankbumber);
            System.out.println("계좌 번호 : " + bankbumber + " 생성 완료!");
            System.out.println("다음 작업을 진행해주세요.");
            System.out.println("==============================");
        }else {
            throw new AccountNotFoundException("현재 은행의 계좌가 5개 이상입니다.");
        }
    }

    public boolean checkMaxAccount() {
        if(CustomerDto.ACC_MAX_SIZE > LoginManager.getInstance().getAccounts().size()) {
            return true;
        }
        return false;
    }

}

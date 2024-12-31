package backendproject.Repository;

import backendproject.Dto.AccountDto;
import backendproject.Service.LoginManager;

import java.util.ArrayList;

public class AccountRepository {

    private DatabaseConnect db = DatabaseConnect.getInstance();

    public void updateBalance(int balance, String banknumber){
        db.connect();
        String sql = "UPDATE account SET balance = ? where seq = ?";
        db.update(sql, balance, getseq(banknumber));
        db.close();
    }

    public int getseq(String banknumber){
        ArrayList<AccountDto> accounts = LoginManager.getInstance().getAccounts();
        for(AccountDto account : accounts){
            if(account.getBankNumber().equals(banknumber)){
                return account.getId();
            }
        }
        return 6; // account 최대 갯수는 5개 6이 리턴되면 오류
    }

    public void createAccount(String banknumber){
        String sql = "INSERT INTO ACCOUNT (customer_id, bank_number, balance) VALUES (?, ?, ?)";
        // 계좌 생성
        db.connect();
        db.insert(sql,LoginManager.getInstance().getSeq(), banknumber, 0);
        db.close();
    }
}

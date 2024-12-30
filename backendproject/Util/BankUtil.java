package backendproject.Util;

import backendproject.Repository.BankRepository;

import java.sql.SQLException;


public class BankUtil {

    private BankRepository bankRepo = new BankRepository();

    void showAllBank() throws SQLException {
        System.out.println("======== 현재 은행 정보 ========");
        int i = 1;
        for(String bankName : bankRepo.readAllBank()){
            System.out.println(i++ + ". " + bankName);
        }
        System.out.println("==============================");
    }
}

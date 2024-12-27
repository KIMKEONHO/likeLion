package backendproject.Service;

import backendproject.Repository.DatabaseConnect;
import backendproject.Dto.AccountDto;
import backendproject.Repository.CustomerRepository;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

@Getter
public class LoginManager {
    private static LoginManager instance;

    private String loggedInUserId; // 로그인한 사용자 ID
    private int seq;  // db에 저장된 사용자의 키값
    private DatabaseConnect db;
    private ArrayList<AccountDto> accounts;

    private CustomerRepository customerRepository = new CustomerRepository();

    private LoginManager() {

    }

    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public boolean login(String inputId, String inputPassword) throws SQLException {
        if (customerRepository.validateUser(inputId, inputPassword)) {
            loggedInUserId = inputId;
            seq = customerRepository.getUserSeq(inputId);
            accounts = customerRepository.getUserAccounts(inputId);
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUserId = null; // 로그아웃 시 사용자 ID 초기화
    }

}

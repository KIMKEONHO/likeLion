package com.example.demo.Service;

import com.example.demo.Dto.AccountDto;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.DatabaseConnect;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Getter
@Service
public class LoginManager {
    private volatile static LoginManager instance;

    private String loggedInUserId; // 로그인한 사용자 ID
    private int seq;  // db에 저장된 사용자의 키값
    private DatabaseConnect db;
    private ArrayList<AccountDto> accounts;

    private CustomerRepository customerRepository = new CustomerRepository();

    private LoginManager() {

    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnect.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
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

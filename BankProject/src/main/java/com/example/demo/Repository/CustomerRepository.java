package com.example.demo.Repository;

import com.example.demo.Dto.AccountDto;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CustomerRepository {

    private DatabaseConnect db = DatabaseConnect.getInstance();

    public boolean validateUser(String inputId, String inputPassword) throws SQLException {
        db.connect();
        String sql = "SELECT user_id, user_password FROM CUSTOMERS WHERE user_id = ?";

        ResultSet rs = db.read(sql, inputId);

        if (rs != null && rs.next()) {
            boolean isValid = inputId.equals(rs.getString("user_id")) &&
                    inputPassword.equals(rs.getString("user_password"));
            db.close();
            return isValid;
        }
        db.close();
        return false;
    }

    public int getUserSeq(String userId) throws SQLException {
        db.connect();
        String sql = "SELECT seq FROM CUSTOMERS WHERE user_id = ?";
        ResultSet rs = db.read(sql, userId);
        if (rs != null && rs.next()) {
            int seq = rs.getInt("seq");
            db.close();
            return seq;
        }
        db.close();
        return -1; // 사용자 없음
    }

    public ArrayList<AccountDto> getUserAccounts(String userId) throws SQLException {
        ArrayList<AccountDto> accounts = new ArrayList<>();
        db.connect();
        String sql = "SELECT seq, bank_number, balance FROM ACCOUNT WHERE customer_id = ?";
        ResultSet rs = db.read(sql, getUserSeq(userId));

        while (rs != null && rs.next()) {
            accounts.add(
                    AccountDto.builder()
                            .id(rs.getInt("seq"))
                            .bankNumber(rs.getString("bank_number"))
                            .balance(rs.getInt("balance"))
                            .build());
        }
        db.close();
        return accounts;
    }

}

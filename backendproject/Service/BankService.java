package backendproject.Service;

import backendproject.Repository.CustomerRepository;
import backendproject.Repository.DatabaseConnect;
import backendproject.Exception.BankOperationException;

import java.sql.*;

public interface BankService {
    DatabaseConnect db = DatabaseConnect.getInstance();
    CustomerRepository customerRepo = new CustomerRepository();

    // 고객 수 제한
    final static int CUS_MAX_SIZE = 10;

    // 고객 중복 체크
    default boolean isIn(String inputId,String inputPw) throws SQLException {
        boolean real = customerRepo.validateUser(inputId,inputPw);
        return real;
    }

    // 고객 저장
    default void saveCustomer(String name, String id, String pw, int bankId){
        try {
            // 중복 체크
            if (isIn(id,pw)) {
                throw new BankOperationException("이미 존재하는 ID입니다. (다른 아이디를 입력해주세요.)");
            }
            String sql = "INSERT INTO customers (user_id, user_password, user_name, bank_id) VALUES (?,?,?,?)";
            db.insert(sql, id, pw, name, bankId);
        } catch (BankOperationException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String createAccountNumber();

}

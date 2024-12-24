package backendproject;

import backendproject.Exception.BankOperationException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public interface Bank {
    DatabaseConnect db = DatabaseConnect.getInstance();

    // 고객 수 제한
    final static int CUS_MAX_SIZE = 10;

    // 고객들 정보를 담은 리스트
    ArrayList<Customer> customers = new ArrayList<>();

    // 고객들 리스트 반환
    ArrayList<Customer> getCustomers();

    // 고객 추가
    void addCustomer(Customer customer) throws BankOperationException;

    // 고객 검색
    Customer findCustomer(int i) throws BankOperationException ;

    // 고객 중복 체크
    boolean isIn(String userId);

    // 고객 저장
    void saveCustomer(String name, String id, String pw, int bankId);

    String createAccountNumber();

    // 고객 목록 출력 Display
    default void showCustomersDiplay() {
        System.out.println("-------- 현재 고객 정보 --------");
        for (Customer c : getCustomers()) {
            System.out.println(c.getName());
        }
        System.out.println("------------------------------");
    }

    default void showAllBank() throws SQLException {
        ResultSet rs = null;
        String sql = "select bank_name from bank";
        db.connect();
        rs = db.read(sql);

        // 결과가 없을 때 처리
        if (rs == null || !rs.next()) {
            System.out.println("현재 아무런 은행이 개설되지 않았습니다.");
        } else {
            System.out.println("현재 은행 목록 --------");
            int i = 1;
            do {
                System.out.println(i + ". " + rs.getString("bank_name")); // 첫 번째 결과 출력
                i+=1;
            } while (rs.next());
        }

        db.close();
    }

}

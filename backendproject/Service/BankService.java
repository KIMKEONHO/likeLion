package backendproject.Service;

import backendproject.Repository.BankRepository;
import backendproject.Repository.DatabaseConnect;
import backendproject.Dto.CustomerDto;
import backendproject.Exception.BankOperationException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public interface BankService {
    DatabaseConnect db = DatabaseConnect.getInstance();

    // 고객 수 제한
    final static int CUS_MAX_SIZE = 10;

    // 고객들 정보를 담은 리스트
    ArrayList<CustomerDto> customers = new ArrayList<>();

    // 고객들 리스트 반환
    ArrayList<CustomerDto> getCustomers();

    // 고객 추가
    void addCustomer(CustomerDto customer) throws BankOperationException;

    // 고객 검색
    CustomerDto findCustomer(int i) throws BankOperationException ;

    // 고객 중복 체크
    boolean isIn(String userId);

    // 고객 저장
    void saveCustomer(String name, String id, String pw, int bankId);

    String createAccountNumber();

}

package backendproject.Service;

import backendproject.Repository.DatabaseConnect;
import backendproject.Dto.CustomerDto;
import backendproject.Exception.BankOperationException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class NhBankService implements BankService {

    // 고객들 정보를 담은 리스트
    ArrayList<CustomerDto> customers = new ArrayList<>();
    private DatabaseConnect db = DatabaseConnect.getInstance();
    private ResultSet rs = null;

    @Override
    public ArrayList<CustomerDto> getCustomers() {
        return null;
    }

    // 고객 추가
    @Override
    public void addCustomer(CustomerDto customer) throws BankOperationException {
        if (customers.size() == CUS_MAX_SIZE) {
            throw new BankOperationException("최대 고객 제한을 초과했습니다.");
        }
        customers.add(customer);
    }

    // 고객 검색
    @Override
    public CustomerDto findCustomer(int i) throws BankOperationException {
        return null;
    }

    // 고객 중복 체크
    @Override
    public boolean isIn(String userId) {
        db.connect();
        String sql = "select * from customers where user_id = ?";
        rs = db.read(sql, userId);
        if (rs == null){
            return true;
        }
        else{
            return false;
        }
    }

    // 고객 저장
    @Override
    public void saveCustomer(String name, String id, String pw, int bankId) {
            try {
                // 중복 체크
                if(isIn(id)){
                    throw new BankOperationException("이미 존재하는 ID입니다. (다른 아이디를 입력해주세요.)");
                }
                String sql = "INSERT INTO customers (user_id, user_password, user_name, bank_id) VALUES (?,?,?,?)";

                db.insert(sql,id,pw,name,bankId);

//                목록 출력
//                Bank.super.showCustomersDiplay();
            }catch (BankOperationException e) {
                System.out.println(e.getMessage());
            }
    }

    @Override
    public String createAccountNumber() {
        Random random = new Random();

        // YYY 부분 생성 (3자리 숫자)
        String yyy = String.format("%03d", random.nextInt(1000));
        // ZZZZ 부분 생성 (4자리 숫자)
        String zzzz1 = String.format("%04d", random.nextInt(10000));
        String zzzz2 = String.format("%04d", random.nextInt(10000));

        // CT 부분 생성 (2자리 문자)
        String ct = String.format("%02d", random.nextInt(100));

        // 최종 계좌번호 생성
        return String.format("%s-%s-%s-%s", yyy, zzzz1, zzzz2, ct);
    }
}

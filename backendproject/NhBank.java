package backendproject;

import java.sql.ResultSet;
import java.util.ArrayList;

public class NhBank implements Bank {

    // 고객들 정보를 담은 리스트
    ArrayList<Customer> customers = new ArrayList<>();
    private DatabaseConnect db = DatabaseConnect.getInstance();
    private ResultSet rs = null;

    @Override
    public ArrayList<Customer> getCustomers() {
        return null;
    }

    // 고객 추가
    @Override
    public void addCustomer(Customer customer) throws BankOperationException {
        if (customers.size() == CUS_MAX_SIZE) {
            throw new BankOperationException("최대 고객 제한을 초과했습니다.");
        }
        customers.add(customer);
    }

    // 고객 검색
    @Override
    public Customer findCustomer(int i) throws BankOperationException {
        Customer returnCustomer = null;
        for (Customer customer : customers) {
            if (customer.getCus_id() == i) {
                returnCustomer = customer;
                return returnCustomer;
            }
        }
        throw new BankOperationException("없는 고객입니다.");
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

                // 목록 출력
//                Bank.super.showCustomersDiplay();
            }catch (BankOperationException e) {
                System.out.println(e.getMessage());
            }
    }

}

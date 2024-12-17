package backendproject;

import java.util.ArrayList;

public class NhBank implements Bank {

    // 고객들 정보를 담은 리스트
    ArrayList<Customer> customers = new ArrayList<>();

    @Override
    public ArrayList<Customer> getCustomers() {
        return null;
    }

    // 고객 추가
    @Override
    public void addCustomer(Customer customer) throws BankOperationException {
        if (customers.size() == MAX_SIZE) {
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
    public boolean isIn(int id) {
        for (Customer customer : customers) {
            if (customer.getCus_id() == id) {
                return true;
            }
        }
        return false;
    }

    // 고객 저장
    @Override
    public void saveCustomer(String name, int id){
        try {
            // 중복 체크
            if(isIn(id)){
                throw new BankOperationException("이미 존재하는 ID입니다. (다른 아이디를 입력해주세요.)");
            }
            // 고객 생성
            Customer customer = Customer.builder()
                    .name(name)
                    .cus_id(id)
                    .build();
            // 고객 목록에 추가
            addCustomer(customer);
            // 목록 출력
            Bank.super.showCustomersDiplay();
        }catch (BankOperationException e) {
            System.out.println(e.getMessage());
        }
    }

}

package backendproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    // 고객 수 제한
    private final static int MAX_SIZE = 10;

    // 고객들 정보를 담은 리스트
    private ArrayList<Customer> customers = new ArrayList<>();

    // 고객들 리스트 반환
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    // 고객 추가
    public void addCustomer(Customer customer) throws BankOperationException {
        if (customers.size() == MAX_SIZE) {
            throw new BankOperationException("최대 고객 제한을 초과했습니다.");
        }

        customers.add(customer);
    }

    // 고객 검색
    public Customer findCustomer(int i) throws BankOperationException {
        Customer returnCustomer = null;

        for (Customer customer : customers) {
            if (customer.getId() == i) {
                returnCustomer = customer;
                return returnCustomer;
            }
        }

        throw new BankOperationException("없는 고객입니다.");
    }

    // 고객 중복 체크
    public boolean isIn(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // 고객 저장
    public void saveCustomer(String name, int id){
        try {

            // 중복 체크
            if(isIn(id)){
                throw new BankOperationException("이미 존재하는 ID입니다. (다른 아이디를 입력해주세요.)");
            }

            // 고객 생성
            Customer customer = Customer.builder()
                    .name(name)
                    .id(id)
                    .build();

            // 고객 목록에 추가
            addCustomer(customer);

            // 목록 출력
            showCustomersDiplay();

        }catch (BankOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    // 고객 목록 출력 Display
    private void showCustomersDiplay() {
        System.out.println("-------- 현재 고객 정보 --------");
        for (Customer c : getCustomers()) {
            System.out.println(c.getName());
        }
        System.out.println("------------------------------");
    }

}

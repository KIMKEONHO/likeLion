package backendproject;

import java.util.ArrayList;

public interface Bank {
    // 고객 수 제한
    final static int MAX_SIZE = 10;

    // 고객들 정보를 담은 리스트
    ArrayList<Customer> customers = new ArrayList<>();

    // 고객들 리스트 반환
    ArrayList<Customer> getCustomers();

    // 고객 추가
    void addCustomer(Customer customer) throws BankOperationException ;

    // 고객 검색
    Customer findCustomer(int i) throws BankOperationException ;

    // 고객 중복 체크
    boolean isIn(int id);

    // 고객 저장
    void saveCustomer(String name, int id);

    // 고객 목록 출력 Display
    default void showCustomersDiplay() {
        System.out.println("-------- 현재 고객 정보 --------");
        for (Customer c : getCustomers()) {
            System.out.println(c.getName());
        }
        System.out.println("------------------------------");
    }

}

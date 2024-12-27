package backendproject.Util;

import backendproject.Dto.CustomerDto;
import backendproject.Exception.BankOperationException;
import backendproject.Service.BankService;

import java.sql.SQLException;
import java.util.ArrayList;

public class BankUtil implements BankService{
    @Override
    public ArrayList<CustomerDto> getCustomers() {
        return null;
    }

    @Override
    public void addCustomer(CustomerDto customer) throws BankOperationException {

    }

    @Override
    public CustomerDto findCustomer(int i) throws BankOperationException {
        return null;
    }

    @Override
    public boolean isIn(String userId) {
        return false;
    }

    @Override
    public void saveCustomer(String name, String id, String pw, int bankId) {

    }

    @Override
    public String createAccountNumber() {
        return "";
    }

    void showAllBank() throws SQLException {
        System.out.println("======== 현재 은행 정보 ========");
        int i = 1;
        for(String bankName : bankRepo.readAllBank()){
            System.out.println(i++ + ". " + bankName);
        }
        System.out.println("==============================");
    }
}

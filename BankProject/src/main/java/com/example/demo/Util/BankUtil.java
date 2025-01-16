package com.example.demo.Util;

import com.example.demo.Repository.BankRepository;
import com.example.demo.Service.BankService;
import com.example.demo.Service.NhBankService;
import lombok.experimental.UtilityClass;

import java.sql.SQLException;
import java.util.Scanner;

@UtilityClass
public class BankUtil {

    private static BankRepository bankRepo = new BankRepository();

    public static void showAllBank() throws SQLException {
        System.out.println("======== 현재 은행 정보 ========");
        int i = 1;
        for(String bankName : bankRepo.readAllBank()){
            System.out.println(i++ + ". " + bankName);
        }
        System.out.println("==============================");
    }

    public static BankService returnInputBank(Scanner scanner ) throws SQLException {
        BankService bank = null;
        showAllBank();
        System.out.print("원하시는 은행을 선택하세요(정수로) : ");
        int bankId = scanner.nextInt();
        switch (bankId){
            case 1:
                bank = new NhBankService();
                // ...  기타 은행들 //
        }
        return bank;
    }

}

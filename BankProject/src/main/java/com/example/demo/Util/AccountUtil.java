package com.example.demo.Util;

import com.example.demo.Dto.AccountDto;
import com.example.demo.Service.LoginManager;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountUtil {

    public static void allAccountDisplay(){
        System.out.println("========현재 고객님의 계좌 정보========");
        int i = 0;
        for(AccountDto accountDto : LoginManager.getInstance().getAccounts()){
            i++;
            System.out.println(i + ". " + accountDto.getBankNumber());
        }
        System.out.println("===================================");
    }

    public static void displayAccountChangeSuccess(AccountDto account){
        System.out.println("현재 " + account.getBankNumber() + "계좌의 잔액 : " + account.getBalance());

        System.out.println("다음 작업을 선택하세요.");
        System.out.println("===================================");
    }
}

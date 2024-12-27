package backendproject.Util;

import backendproject.Dto.AccountDto;
import backendproject.Service.LoginManager;

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
}

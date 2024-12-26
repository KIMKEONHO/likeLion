package backendproject;

import backendproject.Exception.AccountNotFoundException;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    // 고객명
    private String name;

    // db 연결을 위한 객체
    private DatabaseConnect db = DatabaseConnect.getInstance();

    // 고객 주키? id
    private int cus_id;

    // id, password
    private String user_id;
    private String password;

    // 계좌 목록 리스트
    private ArrayList<Account> accounts = new ArrayList<>();

    // 계좌 최대치 변수
    private static final int ACC_MAX_SIZE = 5;

    // 계좌 중복 체크
    public Boolean isIn(String bankNumber) throws AccountNotFoundException {
        boolean found = false;

        for (Account account : accounts) {
            System.out.println(account.getBankNumber());
            if (bankNumber.equals(account.getBankNumber())) {
                found = true;
                return found;
            }
        }

        return found;
    }

    public ArrayList<Account> getAccounts() throws SQLException {
        db.connect();
        String sql = "SELECT * FROM ACCOUNT WHERE customer_id = ?";
        int seq = LoginManager.getInstance().getSeq();

        ArrayList<Account> accounts = new ArrayList<>();
        ResultSet rs = db.read(sql, seq);
        while(true){
            if(rs.next()){
                Account account = Account.builder()
                        .id(seq)
                        .bankNumber(rs.getString("bank_number"))
                        .balance(rs.getInt("balance"))
                        .build();
                accounts.add(account);
            }else{
                break;
            }
        }
        db.close();
        return accounts;
    }

    // 계좌 생성
    public void createAccount(String bankNumber) {
        try {
            // 빈 계좌인지 체크
            String sql = "INSERT INTO ACCOUNT (customer_id, bank_number, balance) VALUES (?, ?, ?)";
            if(getAccounts() != null) {
                // 게좌가 5개 이상인지 체크
                if (getAccounts().size() >= ACC_MAX_SIZE) {
                    throw new AccountNotFoundException("이미 5개의 계좌가 있습니다.");
                }
                // 중복된 계좌인지 체크
                if (isIn(bankNumber)) {
                    throw new AccountNotFoundException("이미 있는 계좌입니다.");
                }

                // 계좌 생성
                db.connect();
                db.insert(sql,LoginManager.getInstance().getSeq(), bankNumber, 0);
                db.close();

            }else {
                // 계좌 생성
                db.connect();
                db.insert(sql,LoginManager.getInstance().getSeq(), bankNumber);
                db.close();

                accounts.add(new Account().builder()
                        .bankNumber(bankNumber)
                        .id(LoginManager.getInstance().getSeq())
                        .balance(0)
                        .build());
            }
            // 계좌 목록 출력
            displayAccounts();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // 계좌 찾기
    public Account findAccount(String banknumber) throws AccountNotFoundException {
        Account result = null;
        for (Account account : accounts) {
            if (banknumber.equals(account.getBankNumber())) {
                result = account;
                return result;
            }
        }
        throw new AccountNotFoundException("해당 계좌가 존재하지 않습니다.");
    }

    // 계좌 목록 출력을 위한 display
    private void displayAccounts() throws SQLException {
        System.out.println("-------- 현재 계좌 목록 --------");
        for (Account account : getAccounts()) {
            System.out.println(account.getBankNumber());
        }
        System.out.println("------------------------------");
    }

    // 계좌 추가
    public void addAccount(Account a) {accounts.add(a);}

}

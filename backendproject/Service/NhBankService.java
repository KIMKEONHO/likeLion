package backendproject.Service;

import backendproject.Dto.CustomerDto;
import java.util.ArrayList;
import java.util.Random;

public class NhBankService implements BankService {

    // 고객들 정보를 담은 리스트
    ArrayList<CustomerDto> customers = new ArrayList<>();
    public static final int NH_CUS_MAX_SIZE = 5;

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

package backendproject.Dto;

import backendproject.Repository.DatabaseConnect;
import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

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
    private ArrayList<AccountDto> accounts = new ArrayList<>();

    // 계좌 최대치 변수
    public static final int ACC_MAX_SIZE = 5;

}

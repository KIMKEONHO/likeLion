package com.example.demo.Dto;

import com.example.demo.Repository.DatabaseConnect;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    // id값
    private int id;

    // 계좌 번호
    private String bankNumber;

    // 잔액
    private int balance;

    private DatabaseConnect db = DatabaseConnect.getInstance();

}

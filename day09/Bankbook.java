package day09;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bankbook {
    private int id;
    private String name;
    private int money;

    public void outMoney(int money) throws BankException {
        if (this.money < money) {
            throw new BankException("잔고 부족: 현재 잔고는 " + this.money + "원입니다.");
        }
        this.money -= money;
        System.out.println("출금 완료: 현재 잔고는 " + this.money + "원입니다.");
    }

    public void inMoney(int money) {
        this.money += money;
        System.out.println("입금 완료: 현재 잔고는 " + this.money + "원입니다.");
    }
}


package day05;

import lombok.*;

import java.util.ArrayList;
import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
//@Slf4j
public class VendingMachine {
    private int id;
    private String name;
    private int price;
    private int quantity;

    public void upId() {
        this.id = id + 1;
    }

    public void downId() {
        this.id = id - 1;
    }

    public static void main(String[] args) {
        ArrayList<VendingMachine> v1 = new ArrayList<>();

        v1.add(VendingMachine.builder()
                .price(1200)
                .name("콜라")
                .id(1)
                .build());

        v1.add(VendingMachine.builder()
                .price(1300)
                .name("사이다")
                .id(2)
                .build());

        v1.add(VendingMachine.builder()
                .price(1400)
                .name("환타")
                .id(3)
                .build());

        v1.add(VendingMachine.builder()
                .price(1500)
                .name("밀키스")
                .id(4)
                .build());

        int menu = 0;

        System.out.println("0. 음료 뽑기");
        System.out.println("1. 음료 넣기");
        System.out.println("2. 종료");
        System.out.print("원하는 작업을 선택하세요 : ");
        Scanner scanner = new Scanner(System.in);

        int i = scanner.nextInt();

        while (i != 2) {
            System.out.println("0. 음료 뽑기");
            System.out.println("1. 음료 넣기");
            System.out.println("2. 종료");
            System.out.print("원하는 작업을 선택하세요 : ");
            scanner = new Scanner(System.in);

            i = scanner.nextInt();
            if (i == 0) {
                for (VendingMachine a : v1) {
                    System.out.println(a.getId() + ". " + a.getName());
                }
                System.out.print("메뉴를 입력하세요 : ");
                Scanner sc = new Scanner(System.in);
                menu = sc.nextInt();
                for (VendingMachine a : v1) {
                    if (a.getId() == menu) {
                        System.out.println(a.getName() + "를 내보냅니다.");
                    }
                }
                System.out.println("종료합니다.");
            } else if (i == 1) {
                System.out.println("원하시는 메뉴의 이름을 입력하세요: ");
                scanner = new Scanner(System.in);
                String menuname = scanner.next();
                System.out.println("원하시는 메뉴의 가격을 입력하세요: ");
                scanner = new Scanner(System.in);
                int menuprice = scanner.nextInt();
                System.out.println("원하시는 메뉴의 ID를 입력하세요: ");
                scanner = new Scanner(System.in);
                int menuId = scanner.nextInt();

                v1.add(VendingMachine.builder()
                        .price(menuprice)
                        .name(menuname)
                        .id(menuId)
                        .build());
            }
        }

    }

}

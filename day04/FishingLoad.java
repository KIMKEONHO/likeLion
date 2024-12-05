package day04;

import lombok.*;

import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FishingLoad {
    private String rill;
    private String fish;
    private String line;
    public boolean openRill(int i){
        if (i==1){
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        FishingLoad f = FishingLoad.builder()
                .fish("감섬돔")
                .rill("2000번대 릴")
                .line("3호줄")
                .build();

        System.out.println(f.getFish()+"전용 낚시대" + f.getRill() + f.getLine() + "을 사용중입니다.");
        System.out.print("낚시대를 던지겠습니까?(Yes or No)");
        Scanner sc = new Scanner(System.in);

        if(sc.equals("Yes")){
            boolean status = f.openRill(1);
        } else if (sc.equals("No")) {
            boolean status = f.openRill(0);
        }

    }
}

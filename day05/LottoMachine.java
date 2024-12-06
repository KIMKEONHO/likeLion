package day05;

import java.util.ArrayList;
import java.util.Random;

public class LottoMachine {
    ArrayList<LottoBall> balls = new ArrayList<LottoBall>();

    public LottoMachine() {
        for (int i = 1; i <= 45; i++) {
            LottoBall ball = new LottoBall(i);
            this.balls.add(ball);
        }
    }

    public static void main(String[] args) {
        int[] result = new int[6];
        ArrayList<Integer> selectedNumbers = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int a;
            boolean isDuplicate;

            do {
                a = rand.nextInt(1, 46);
                isDuplicate = false;

                for (int num : selectedNumbers) {
                    if (num == a) {
                        isDuplicate = true;
                        break;
                    }
                }

            } while (isDuplicate);

            selectedNumbers.add(a);
            result[i] = a;
        }

        for (int num : result) {
            System.out.println(num);
        }
    }
}

package day04;

import java.util.Random;

public class Dice {
    int face;
    int eye;

    Random rand = new Random();
    public int roll(){

        // return rand.nextInt(6) + 1;

        // 0.0 ~ 1.0
        return (int)(Math.random() * 6) + 1;
    }
}

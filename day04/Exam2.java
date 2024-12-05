package day04;


public class Exam2 {

    public int addInt(int val1, int val2) {
        return val1 + val2;
    }

    // 가변인자로써 배열을 직접 선언하지 않고도 자유롭게 받을 수 있다.
    public static int addInt(int ... values) {
        int sum = 0;
        for(int val : values) {
            sum += val;
        }
        return sum;
    }

    public static void main(String[] args) {

        addInt(1,2,3,4,5);
    }
}


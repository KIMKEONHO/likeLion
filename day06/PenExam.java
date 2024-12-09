package day06;

public class PenExam {
    public static void main(String[] args) {
        Pen p1 = Pen.builder()
                .color("Blue")
                .price(1500)
                .nmae("모나미 파란색 봂팬")
                .build();

        Pen p2 = Pen.builder()
                .color("Red")
                .price(1500)
                .build();

        System.out.println(p1.getNmae());
        System.out.println(p1.getColor());

    }
}

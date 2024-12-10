package day07;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Triangle extends Shape{

    private double hight;
    private double weight;

    @Override
    public double getarea() {
        return weight * hight / 2;
    }

    @Override
    public void draw() {
        System.out.println("Triangle Draw");
    }
}

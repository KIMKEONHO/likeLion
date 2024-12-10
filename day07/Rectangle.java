package day07;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rectangle extends Shape{

    private double length;

    @Override
    public double getarea() {
        return length*length;
    }

    @Override
    public void draw() {
        System.out.println("draw rectangle");
    }


}

package sample.bean;

import lombok.Getter;
import lombok.Setter;
@Getter
public class Book {
    @Setter
    private String title;
    private int price;

    public Book() {
        System.out.println("Book Constructor");
    }

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

}

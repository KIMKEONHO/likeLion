package day03;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
//@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemForArray {
    private String itemName;
    private int itemPrice;

    public static class Main{
        public static void main(String[] args) {
            ItemForArray item = ItemForArray.builder()
                    .itemName("인피")
                    .itemPrice(3600)
                    .build();
        }
    }

}

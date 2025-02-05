package Task;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LamdaTest {
    public static void main(String[] args) {
        Supplier<Integer> onlyreturn1 = ()->1; // 오직 1만을 리턴하는 메서드
        Consumer<Integer> onlyCalcul = (a)->{
            System.out.println(a + " ");
        };
        Function<Integer, String> f = (a)->{ return "반환 타입이 String" + a.toString();};
        Predicate<String> p = (a)->{return true;};

        // 사용 예시
        System.out.println(onlyreturn1.get()); // 1을 출력
        onlyCalcul.accept(1); // 1을 입력받아 1 을 출력
        System.out.println(f.apply(1)); // function 함수에 1이라는 매개 변수를 받아 출력
        if(p.test("b")) System.out.println("야호");;  // 약간 중복 체크, id 체크 같은 거 할 때 좋을듯


    }
}

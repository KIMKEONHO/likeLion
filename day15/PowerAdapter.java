package day15;

public class PowerAdapter implements Power110v{
    private Power220v power220v;
    public PowerAdapter(Power220v power220v) {
        this.power220v = power220v;
    }

    @Override
    public void suply() {
        System.out.println("어댑터를 사용해서 220v를 110v로 연결");
        power220v.connect();
    }
}

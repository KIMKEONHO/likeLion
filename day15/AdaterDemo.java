package day15;

public class AdaterDemo{
    public static void main(String[] args) {
        Power220v power220v = new Power220v();
        Power110v adapter = new PowerAdapter(power220v);
        adapter.suply();
    }
}

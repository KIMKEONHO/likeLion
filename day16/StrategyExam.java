package day16;

interface PaymentStrategy {
    void pay(int amount);
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(int amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("결제 전략이 설정되지 않았습니다.");
        }
    }
}

class KakaoPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("카카오 결제 금액은 " + amount + "원입니다.");
    }
}

class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("신용카드 결제 금액은 " + amount + "원입니다.");
    }
}

public class StrategyExam {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setPaymentStrategy(new CreditCardPaymentStrategy());
        paymentContext.executePayment(10000);

        paymentContext.setPaymentStrategy(new KakaoPaymentStrategy());
        paymentContext.executePayment(5000);

    }
}

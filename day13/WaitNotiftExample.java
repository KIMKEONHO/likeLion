package day13;

public class WaitNotiftExample {
    static class Producer extends Thread {
        public void run() {
            synchronized (lock) {
                System.out.println("생산자가 아이템을 생산하는 중!");
                itemsAvailable += 5;
                lock.notifyAll();
                System.out.println("생산자가 알림을 보냄");
            }
        }
    }

    static class Consumer extends Thread {
        String name;
        public void run() {
            System.out.println("소비자 시작");
            synchronized (lock){
                while (itemsAvailable == 0) {
                    System.out.println("아이템을 기다리는중");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("아이템을 소비함.");
                itemsAvailable -= 1;
                System.out.println("소비자 " + name + "님이 아이템을 소비했습니다. 남은 아이템 수 : " + itemsAvailable);
            }
        }
        public Consumer(String name){
            this.name = name;
        }
    }

    private static final Object lock = new Object();
    private static int itemsAvailable = 0;

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer("kang");
        Consumer consumer2 = new Consumer("kim");
        Consumer consumer3 = new Consumer("hong");
        consumer.start();
        producer.start();
        consumer2.start();
        consumer3.start();
    }
}



import java.util.*;

class HelloWorld {

    public static int counter = 1;

    public synchronized void displayCunterAfter() throws InterruptedException {

        for(int i=1; i<40; i++) {
            System.out.println("Display counter :" + counter);

            if(counter == 20) {
                System.out.println("Waiting for counter update :");
                wait();
            }

            counter++;
        }
    }

    public synchronized void inCreaseCounter() {
        counter += 0;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {

        HelloWorld helloWorld = new HelloWorld();
        Thread t = new Thread(() -> {
            try {
                helloWorld.displayCunterAfter();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            helloWorld.inCreaseCounter();
        });

        t.start();
        t2.start();

    }
}

class MyThread extends Thread {

    A a = new A();
    B b = new B();

    public void startThread() {
        this.start();
        a.m1(b);
    }

    @Override
    public void run() {
        System.out.println("My Thread start:");
        b.m1(a);
    }
}

class A {

    public synchronized void m1(B b) {
        try {
            Thread.sleep(2000);
        } catch(Exception e) {

        }
        System.out.println("M1 in A");
        b.m2();
    }

    public synchronized void m2() {
        System.out.println("M2 in A");
    }
}

class B {
    public synchronized void m1(A a) {
        try {
            Thread.sleep(2000);
        } catch(Exception e) {

        }
        System.out.println("M1 in B");
        a.m2();
    }
    public synchronized void m2() {
        System.out.println("M2 in B");
    }
}
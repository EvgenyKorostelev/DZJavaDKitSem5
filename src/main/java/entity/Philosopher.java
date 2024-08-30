package entity;

import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {

    private final String name;
    private boolean hungryStatus = true;
    private volatile Fork leftFork;
    private volatile Fork rightFork;
    private final CountDownLatch cdlPhilosopher;
    private CountDownLatch count;


    public Philosopher(String name, CountDownLatch count) {
        this.name = name;
        this.cdlPhilosopher = new CountDownLatch(3);
        this.count = count;
    }

    public CountDownLatch getCount() {
        return count;
    }

    public void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    public void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }

    public void takeLeftFork() {
        this.leftFork.setOccupationStatus(true);
    }

    public void takeRightFork() {
        this.rightFork.setOccupationStatus(true);
    }

    public void putLeftForkOnTable() {
        this.leftFork.setOccupationStatus(false);
    }

    public void putRightForkOnTable() {
        this.rightFork.setOccupationStatus(false);
    }

    public void eat() {
        takeLeftFork();
        takeRightFork();
        System.out.println(this.name +
                " ест спагетти и приговаривает: Мамма Mia!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        putLeftForkOnTable();
        putRightForkOnTable();
        this.hungryStatus = false;
    }

    public void meditate() {
        System.out.println(this.name +
                " задумался о Великом . . .");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.hungryStatus = true;
    }

    @Override
    public void run() {
        while (cdlPhilosopher.getCount() > 0) {
            if (!leftFork.isOccupationStatus()
                    && !rightFork.isOccupationStatus()
                    && this.hungryStatus) {
                eat();
                cdlPhilosopher.countDown();
            } else {
                meditate();
            }
            if (cdlPhilosopher.getCount() == 0) {
                System.out.println(this.name + " НАЕЛСЯ");
                count.countDown();
                count.getCount();

            }
        }
    }
}

package entity;

import java.util.List;

public class Table extends Thread {

    private List<Philosopher> philosophers;
    private List<Fork> forks;


    public Table(List<Philosopher> philosophers, List<Fork> forks) {
        this.philosophers = philosophers;
        this.forks = forks;
        layOutTheCutlery();
    }

    private void layOutTheCutlery() {
        if (philosophers.size() < forks.size()) {
            for (int i = 0; i < forks.size() - philosophers.size(); i++) {
                forks.removeLast();
            }
        } else {
            for (int i = 0; i < philosophers.size() - forks.size(); i++) {
                forks.add(new Fork());
            }
        }
        for (int i = 0; i < philosophers.size(); i++) {
            if (i < philosophers.size() - 1) {
                philosophers.get(i).setLeftFork(forks.get(i));
                philosophers.get(i).setRightFork(forks.get(i + 1));
            } else {
                philosophers.get(i).setLeftFork(forks.get(i));
                philosophers.get(i).setRightFork(forks.getFirst());
            }
        }
    }

    public void startLunch() {
        System.out.println("""
                
                *** Мыслители уселись за стол, ***
                *** обед начинается ! ! ! ***
                """);
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (!thread.isDaemon()) {
                System.out.println(thread);
            }
        }
        System.out.println();
        while (true){
            if(philosophers.getFirst().getCount().getCount() == 0){
                System.out.println("""

                *** Великие умы закончили свой обед, ***
                *** сытые и довольные отправились отдыхать. ***""");
                break;
            }
        }
    }

    @Override
    public void run() {
        startLunch();
    }
}

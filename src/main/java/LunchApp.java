import entity.Fork;
import entity.Philosopher;
import entity.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LunchApp {
    static final CountDownLatch COUNT = new CountDownLatch(5);
    public static void main(String[] args) {
        List<Philosopher> philosophers = new ArrayList<>(List.of(
                new Philosopher("Платон", COUNT),
                new Philosopher("Сократ", COUNT),
                new Philosopher("Ницше", COUNT),
                new Philosopher("Марк Аврелий", COUNT),
                new Philosopher("Аристотель", COUNT))
        );
        List<Fork> forks = new ArrayList<>(List.of(
                new Fork(),
                new Fork(),
                new Fork(),
                new Fork(),
                new Fork()
        ));
        Table table = new Table(philosophers, forks);
        table.startLunch();
    }
}

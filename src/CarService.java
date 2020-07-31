import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CarService {

    private final CyclicBarrier waitForPrepareBarrier;
    private FinishNotifier finishNotifier;

    public CarService(int carCount, FinishNotifier finishNotifier, Runnable aftreStartAction) {
        this.waitForPrepareBarrier = new CyclicBarrier(carCount + 1, aftreStartAction);
        this.finishNotifier = finishNotifier;
    }

    public Car createCar(Race race, int speed) {
        return new Car(race, speed, waitForPrepareBarrier, finishNotifier);
    }

    public void awaitingAllCarsStarted() {
        try {
            waitForPrepareBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


}

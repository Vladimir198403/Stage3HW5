import java.util.concurrent.CountDownLatch;

public class FinishNotifier {

    private final CountDownLatch countDownLatch ;

    public FinishNotifier(int carsCoutn) {
        this.countDownLatch = new CountDownLatch(carsCoutn);
    }

    public void waitWhileAllCarFinish(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyAboutFinished(){
        countDownLatch.countDown();
    }

}

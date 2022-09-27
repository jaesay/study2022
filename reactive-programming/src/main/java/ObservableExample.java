import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("deprecation")
public class ObservableExample {

  public static void main(String[] args) {
    Observer observer = new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        System.out.println(Thread.currentThread().getName() + " " + arg);
      }
    };

    IntObservable observable = new IntObservable();
    observable.addObserver(observer);

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(observable);

    System.out.println(Thread.currentThread().getName() + " EXIT");
    executorService.shutdown();
  }

  static class IntObservable extends Observable implements Runnable {
    @Override
    public void run() {
      for(int i=1; i<=10; i++) {
        setChanged();
        notifyObservers(i); // push
      }
    }
  }
}

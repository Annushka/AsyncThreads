import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Анна
 * Date: 04.07.13
 * Time: 20:52
 * To change this template use File | Settings | File Templates.
 */
public class Threads_async {
    public class MyCallable implements Callable
    {      public int result, first, last;
        int []a;
        public MyCallable(int first, int last , int[] a) {
            this.first = first;
            this.last = last;
            this.a = a;
        }
        public Integer call () throws java.io.IOException {
            for (int i = first; i < last; i++) {                              // ЗАДАНИЕ!!!!
                if (result > a[i]) {
                    result = a[i];
                }
            }
            return result;
        }
    }

    public void performer (){
        int[] a = {10, 5, 0, 8, -2, 9, 3, 2, -10};
        FutureTask task1 = new FutureTask (new MyCallable (0,(int)a.length/2,a));
        FutureTask task2 = new FutureTask (new MyCallable ((int)a.length/2, a.length,a));
        ExecutorService es = Executors.newSingleThreadExecutor ();
        es.submit (task1);
        es.submit (task2);
        try {
            // int result = task.get();                       /// autoboxing support how to use??????
            // System.out.println ("Result from task.get () = " + result);
            System.out.println ("Result from task.get () = " + task1.get());
            System.out.println ("Result from task.get () = " + task2.get());
        }
        catch (Exception e) {
            System.err.println (e);
        }
        es.shutdown ();
    }

    public static void main(String[] args) throws InterruptedException {
        Threads_async A = new Threads_async();
        A.performer();

    }
}


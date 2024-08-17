package examination.tencent;

import java.util.Arrays;
import java.util.Comparator;

public class Test1 {

    public ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

    public static void main(String[] args) {

        Integer[] integers = {5,6,7,1,2,6};
        Arrays.sort(integers, (a, b)->{return b - a;});
        System.out.println(Arrays.toString(integers));
        Thread t1 = new Thread(()->{
            Test1 t = new Test1();
            t.threadLocal.set("dzy 1");
            System.out.println(t.threadLocal.get());
        });

        Thread t2 = new Thread(()->{
            Test1 t = new Test1();
//            t.threadLocal.set("dzy 2");
            System.out.println(t.threadLocal.get());
        });

        t1.start();
        t2.start();


    }

}

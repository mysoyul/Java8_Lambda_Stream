package lambdasinaction;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public class LambdaTest {
    /*
        java.util.function 에 제공하는 함수형 인터페이스
        Consumer -  void accept(T t)
        Predicate - boolean test(T t)
        Supplier - T get()
        Function - R apply(T t)
        Operator -
           UnaryOperator : R apply(T t)
           BinaryOperator : R apply(T t, U u)
    */
    @Test
    public void lambdaTest() {
        //Functional Interface가 가진 추상메서드를 재정의할때 람다식으로 작성하기

        /*
            class MyRunnable implements Runnable {
                  void run() { }
            }
            Thread t1 = new Thread(new MyRunnable());
         */
        //1. Anonymous Inner Class
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Inner Class");
            }
        });
        t1.start();

        //2. Lambda Expression
        Thread t2 = new Thread(() -> System.out.println("Lambda Expression"));
        t2.start();


        List<String> stringList = List.of("abc", "java", "boot"); //Immutable List
        //1. Anonymous Inner Class
        stringList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        });

        //2.Lambda Expression
        stringList.forEach(val -> System.out.println(val));
        //3.Method Reference
        stringList.forEach(System.out::println);
    }


}
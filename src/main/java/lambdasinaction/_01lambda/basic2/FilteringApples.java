package lambdasinaction._01lambda.basic2;

import java.util.*;
import java.util.function.Predicate;

public class FilteringApples{

    public static void main(String ... args){

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"),
                new Apple(160, "red"));

        // 람다식 사용 [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        filterApples(inventory, apple -> apple.getColor().equals("green"))
                .forEach(System.out::println);

        // 람다식 사용[Apple{color='green', weight=155}]
        filterApples(inventory, apple -> apple.getWeight() >= 150)
                .forEach(System.out::println);

        // Method Reference 사용 [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        filterApples(inventory, FilteringApples::isGreenApple);

        // Method Reference 사용 [Apple{color='green', weight=155}]
        filterApples(inventory, FilteringApples::isHeavyApple);

        Predicate<Apple> redApple = apple -> apple.getColor().equals("red");
        Predicate<Apple> notRedApple = redApple.negate();

        System.out.println("Predicate or() 메서드");
        Predicate<Apple> orPredicate = notRedApple.or(a -> a.getWeight() > 150);
        filterApples(inventory, orPredicate)
                .forEach(System.out::println);

        System.out.println("Predicate and() 메서드");
        Predicate<Apple> andPredicate = notRedApple.and(a -> a.getWeight() > 150);
        filterApples(inventory, andPredicate)
                .forEach(System.out::println);
        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        System.out.println(weirdApples);
    }


    public static boolean isGreenApple(Apple apple) {
        return apple.getColor().equals("green");
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate){
        ArrayList<Apple> apples = new ArrayList<>();
        for (Apple apple: inventory) {
            if(predicate.test(apple)){
                apples.add(apple);
            }
        }
        return apples;
    }
}

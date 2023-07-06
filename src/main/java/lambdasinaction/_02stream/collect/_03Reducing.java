package lambdasinaction._02stream.collect;

import static java.util.stream.Collectors.*;
import static lambdasinaction._02stream.collect.Dish.menu;

public class _03Reducing {

    public static void main(String ... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    //1. Collectors.reducing() 사용 - 칼로리 합
    private static int calculateTotalCalories() {
        return 0;
    }
    //2. Method Reference 로 Collectors.reducing() 사용
    private static int calculateTotalCaloriesWithMethodReference() {
        return 0;
    }
    //3.map() 사용
    private static int calculateTotalCaloriesWithoutCollectors() {
        return 0;
    }

    //4. mapToInt() 사용
    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }
}
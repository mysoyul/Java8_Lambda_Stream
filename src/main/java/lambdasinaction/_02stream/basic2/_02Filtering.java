package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lambdasinaction._02stream.basic1.Dish;

import static java.util.stream.Collectors.toList;
import static lambdasinaction._02stream.basic1.Dish.menu;

public class _02Filtering {

    public static void main(String...args){

        // 1. Filtering with predicate ( isVegeterian() )
        List<Dish> vegeList =
                menu.stream()
                        //.filter(dish -> dish.isVegetarian())
                        .filter(Dish::isVegetarian)
                        .collect(toList());

        // 2. Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);



        //3. Truncating 3 stream ( d.getCalories() > 300 )
        //List<Dish> dishesLimit3 =


        //4. Skipping elements
        //List<Dish> dishesSkip2 =



    }
}

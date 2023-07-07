package lambdasinaction._02stream.basic2;
import lambdasinaction._02stream.basic1.*;

import java.util.*;

import static lambdasinaction._02stream.basic1.Dish.menu;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(allMatchMenu());
        System.out.println(noneMatchMenu());
        
        Optional<Dish> dishOptional = findVegetarianDish();
        dishOptional.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream()
                .anyMatch(Dish::isVegetarian);
    }
    //2.allMatch
    private static boolean allMatchMenu(){
        return menu.stream()
                .allMatch(dish -> dish.getCalories() <= 800);
    }

    //3. noneMatch
    private static boolean noneMatchMenu(){
        return menu.stream()
                .noneMatch(dish -> dish.getCalories() > 800);
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){
//        return Optional.empty();  //empty 한 Optional
        return menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
    
}

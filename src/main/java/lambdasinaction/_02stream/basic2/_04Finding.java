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
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }
    //2.allMatch
    private static boolean allMatchMenu(){

        return false;
    }

    //3. noneMatch
    private static boolean noneMatchMenu(){

        return false;
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){

        return null;
    }
    
}

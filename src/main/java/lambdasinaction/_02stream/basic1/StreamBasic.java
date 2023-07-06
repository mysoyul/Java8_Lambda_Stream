package lambdasinaction._02stream.basic1;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
        System.out.println("===> getHighCaloricDishesNamesInJava8MethodRef");
        getHighCaloricDishesNamesInJava8MethodRef(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() <= 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0,3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        List<String> dishNameList = dishes.stream() //Stream<Dish>
                //.filter(Predicate) T -> boolean
                .filter(dish -> dish.getCalories() <= 400) //Stream<Dish>
                //sorted(Comparator) comparing() 메서드의 Function<Dish,Integer>
                .sorted(Comparator.comparing(dish -> dish.getCalories())) //Stream<Dish>
                //map(Function) Function<Dish,String>
                .map(dish -> dish.getName()) //Stream<String>
                .collect(Collectors.toList());
        return dishNameList.subList(0,3);
    }
    public static List<String> getHighCaloricDishesNamesInJava8MethodRef(List<Dish> dishes){
        Comparator<Dish> reverseComparator = Collections.reverseOrder(comparing(Dish::getCalories));

        return dishes.stream()
                .filter(dish -> dish.getCalories() > 400)
                .sorted(reverseComparator)
                .map(Dish::getName)
                .collect(toList());
    }


    //400칼로리 이하인 메뉴를 다이어트로, 아닐 경우 일반으로 그룹핑해라.
    public static Map<String, List<Dish>>  getGroupingMenu(List<Dish> dishes){
        return null;

    }


    //가장 칼로리가 높은 메뉴를 찾아라
    public static Dish getMaxCaloryDish (List<Dish> dishes) {
        return null;


    }
}

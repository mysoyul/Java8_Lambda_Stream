package lambdasinaction._02stream.collect;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.stream.Collectors.*;
import static lambdasinaction._02stream.collect.Dish.menu;

public class _04GroupingDishes {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
        System.out.println("Average calories by caloric level: " + averageCalroiesByCaloricLevel());
    }

    //1. type별 그룹핑
    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream()
                .collect(groupingBy(getDishType()));
    }

    private static Function<Dish, Dish.Type> getDishType() {
        return Dish::getType;
    }

    //2. 칼로리별 그룹핑
    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream()
                .collect(groupingBy(getCaloricLevel()));
    }

    private static Function<Dish, CaloricLevel> getCaloricLevel() {
        return dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        };
    }

    //3. type별로 그룹핑 후에 다시 칼로리별로 그룹핑
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream()
                .collect(groupingBy(getDishType(), groupingBy(getCaloricLevel())));
    }
    //4. type별 갯수 카운팅
    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream()
                .collect(groupingBy(getDishType(), counting()));
    }
    //5. type별 그룹에서 가장 칼로리가 높은 Dish 찾기
    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream()
                .collect(groupingBy(getDishType(), maxBy(getDishComparator())));
    }

    private static Comparator<Dish> getDishComparator() {
        return Comparator.comparingInt(Dish::getCalories);
    }

    //5.1 type별 그룹에서 가장 칼로리가 높은 Dish 찾기 - collectingAndThen() 사용
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return menu.stream()
                .collect(groupingBy(getDishType(), collectingAndThen(
                        maxBy(getDishComparator()), Optional::get
                )));
    }

    private static Collector<Dish, Object, Dish> getDownstream() {
        return collectingAndThen(
                maxBy(getDishComparator()), Optional::get
        );
    }

    //6. type별로 그룹핑하여 칼로리의 합계 내기
    private static Map<Dish.Type, Integer> sumCaloriesByType() {

        return menu.stream()
                .collect(groupingBy(getDishType(), summingInt(Dish::getCalories)));
    }

    //7. Dish Type별로 그룹핑하여 해당 그룹의 CaloricLevel을 Set로 출력하기
    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(getDishType(), mapping(getCaloricLevel(), toSet())));
    }

    //8. CaloricLevel별로 그룹핑하여 평균 칼로리 계산하기
    private static Map<CaloricLevel, Double> averageCalroiesByCaloricLevel() {
        return menu.stream()
                .collect(groupingBy(getCaloricLevel(), averagingDouble(Dish::getCalories)));
    }
}

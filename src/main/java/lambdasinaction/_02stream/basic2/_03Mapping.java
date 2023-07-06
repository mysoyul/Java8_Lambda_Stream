package lambdasinaction._02stream.basic2;

import lambdasinaction._02stream.basic1.*;

import java.util.*;
import static java.util.stream.Collectors.toList;
import static lambdasinaction._02stream.basic1.Dish.menu;

public class _03Mapping {

    public static void main(String...args){

        //1. map - Dish의 name 목록만 List<String>
        List<String> nameList = menu.stream() //Stream<Dish>
                //.map(dish -> dish.getName()) //Stream<String>
                .map(Dish::getName)
                .collect(toList());
        nameList.forEach(System.out::println);

        //2. Dish 칼로리 합계
        //2.1 map() / reduce()
        Integer sumOfCalory = menu.stream()
                .map(Dish::getCalories) //Stream<Integer>
                //BinaryOperator R apply(T t, U u)
                .reduce(0, (prev, next) -> prev + next);
        System.out.println("1. sumOfCalory = " + sumOfCalory);

        //2.1.1 map() / reduce(Integer::sum)
        //Integer의 public static int sum(int a, int b)
        sumOfCalory = menu.stream()
                .map(Dish::getCalories) //Stream<Integer>
                .reduce(Integer::sum) //Optional<Integer>
                .get();
        System.out.println("2. sumOfCalory = " + sumOfCalory);

        //2.2 mapToInt() / sum()
        //2.3 collect(Collector) Collectors.summingInt()

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        //2. map - 중복된 문자 제거한 word 리스트


        //3.flatMap - 중복된 문자 제거가 word 리스트




        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}

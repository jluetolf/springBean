package com.luetolf.SpringBeans;

import com.luetolf.lambdas.Square;
import com.luetolf.lambdas.SquareImp;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//https://www.geeksforgeeks.org/functional-interfaces-java/

@SpringBootTest
public class LambdaTest {

    @Test
    public void squareInterface() {

        int a = 5;
        // lambda expression to define the calculate method
        Square s = x -> x * x;

        // parameter passed and return type must be
        // same as defined in the prototype
        int ans = s.calculate(a);
        assertThat(ans).isEqualTo(25);
    }

    @Test
    public void squareMethod() {
        int a = 5;

        //Square s = (SquareImp::calculate);

    }

    @Test
    public void predicates() {

        // create a list of strings
        List<String> names =
                Arrays.asList("Geek", "GeeksQuiz", "g1", "QA", "Geek2");

        // declare the predicate type as string and use
        // lambda expression to create object
        Predicate<String> p = s -> s.startsWith("G");

        List<String> newList = names
                .stream()
                .filter(s -> p.test(s))
                .collect(Collectors.toList());

        assertThat(newList).isEqualTo(Arrays.asList("Geek", "GeeksQuiz", "Geek2"));

    }

    //https://stackoverflow.com/questions/20001427/double-colon-operator-in-java-8
    @Test
    public void reduce1() {

//        reduce(new IntBinaryOperator() {
//            int applyAsInt(int left, int right) {
//                return Math.max(left, right);
//            }
//        });
    }
}



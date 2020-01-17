package com.luetolf.SpringBeans;

import com.luetolf.streams.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/

@SpringBootTest
public class StreamTests {

    List<Person> persons;

    @Before
    public void Initialize() {
        this.persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));
    }

    @Test
    public void filterList() {

        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        List<String> newList = myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        assertThat(newList).isEqualTo(Arrays.asList("C1", "C2"));
    }

    @Test
    public void filterStream() {
        //same as above, using directly the stream instead of the list
        List<String> newList = Stream.of("a1", "a2", "b1", "c2", "c1")
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        assertThat(newList).isEqualTo(Arrays.asList("C1", "C2"));
    }

    @Test
    public void rangeStream() {

        List<Integer> newList = IntStream.range(1, 4)
                .boxed()
                .collect(Collectors.toList());

        assertThat(newList).isEqualTo(Arrays.asList(1, 2, 3));
    }

    @Test
    public void mapStream() {
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(i -> assertThat(i).isEqualTo(3));  // 3

    }

    @Test
    public void mapStreamDouble() {
        List<String> newList = Stream.of(1.0, 2.0, 3.0)
               // .mapToInt(Double::intValue)
                .mapToInt(aDouble -> {return aDouble.intValue();})
                .mapToObj(i -> "a" + i)
                .collect(Collectors.toList());

        assertThat(newList).isEqualTo(Arrays.asList("a1", "a2", "a3"));
    }
}

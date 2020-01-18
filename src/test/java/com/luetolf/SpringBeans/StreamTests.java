package com.luetolf.SpringBeans;

import com.luetolf.streams.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.*;
import java.util.function.Supplier;
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
                .mapToInt(aDouble -> aDouble.intValue())
                .mapToObj(i -> "a" + i)
                .collect(Collectors.toList());

        assertThat(newList).isEqualTo(Arrays.asList("a1", "a2", "a3"));
    }

    @Test
    public void filerFirstForPerformance() {

        List<String> newList = Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"))
                .sorted((s1, s2) -> s1.compareTo(s2))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        assertThat(newList).isEqualTo(Arrays.asList("A2"));
    }

    @Test
    public void reusingStream() {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        assertThat(streamSupplier.get().anyMatch(s -> true)).isEqualTo(true);   // ok
        assertThat(streamSupplier.get().noneMatch(s -> true)).isEqualTo(false);
        ;  // ok
    }

    public void personFilter() {
        List<String> filtered =
                persons
                        .stream()
                        .filter(p -> p.name.startsWith("P"))
                        .map(person -> person.name)
                        .collect(Collectors.toList());

        assertThat(filtered).isEqualTo(Arrays.asList("Peter", "Pamela"));
    }

    @Test
    public void maptomap() {
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        // age 18: [Max]
        // age 23: [Peter, Pamela]
        // age 12: [David]
    }

    @Test
    public void ageSummary() {
        IntSummaryStatistics ageSummary =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);
        // IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
    }

    @Test
    public void joining() {
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);
        // In Germany Max and Peter and Pamela are of legal age.

    }

    @Test
    public void toMapCollector() {
        Optional<Person> person = persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2);// Pamela

        assertThat(person.get().name).isEqualTo("Pamela");

// {18=Max, 23=Peter;Pamela, 12=David}
    }

    @Test
    public void reduceOlderPerson() {
        Person result =
                persons
                        .stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.age += p2.age;
                            p1.name += p2.name;
                            return p1;
                        });

        assertThat(result.name).isEqualTo("MaxPeterPamelaDavid");
        assertThat(result.age).isEqualTo(76);
    }

    @Test
    public void reducePerson() {
        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
// {18=Max, 23=Peter;Pamela, 12=David}
    }


}


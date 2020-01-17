package com.luetolf.streams;


import java.util.Arrays;
import java.util.List;

// https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
public class Streams {

    List<Person> persons;

    public Streams () {
        this.persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));
    }

    public void steamTest() {


    }
}

package com.luetolf;

import com.luetolf.TestBeans;
import com.luetolf.beans.Animal;
import com.luetolf.beans.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Component
public class TestBeansImpl implements TestBeans {

    @Autowired
    public Set<Animal> myAnimals;

    public TestBeansImpl(Set<Animal> animals, Animal oneBeast) {
        log.info("Constructor TestBeansImpl");
        animals.stream().forEach(animal -> log.info("TestBeansImpl: {}", animal.makeNoise()));
        log.info("The beast is {}", oneBeast.makeNoise()); //see AnimalConfig (annotation @Primary)
    }

    public void test() {
        myAnimals.stream().forEach(animal -> log.info("TestBeansImpl: {}", animal.makeNoise()));
    }
}

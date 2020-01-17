package com.luetolf.SpringBeans;

import com.luetolf.AnimalConfigTest;
import com.luetolf.beans.Animal;
import com.luetolf.beans.Cat;
import com.luetolf.beans.Dog;
import com.luetolf.beans.TestCat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//https://reflectoring.io/spring-boot-test/#customizing-the-application-context

@SpringBootTest
@Import({AnimalConfigTest.class})
public class AnimalTests {

    @Autowired
    @Qualifier("testCat")
    Animal testCat;

    @Autowired
    Animal cat; //the @Primary one

    @Autowired
    @Qualifier("testDog")
    Animal testDog;


    @Test
    void primaryAnimalTest() {
        assertThat(testCat.makeNoise()).isEqualTo(TestCat.noise);
        assertThat(cat.makeNoise()).isEqualTo(Cat.noise);
        assertThat(testDog.makeNoise()).isEqualTo(Dog.noise);
    }

}

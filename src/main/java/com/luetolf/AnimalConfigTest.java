package com.luetolf;

import com.luetolf.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class AnimalConfigTest {

    @Bean
    public Animal testDog() {
        return new Dog();
    };

    @Bean
    public Animal testCat() {
        return new TestCat();
    };
}

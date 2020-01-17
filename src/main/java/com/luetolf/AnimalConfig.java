package com.luetolf;

import com.luetolf.beans.Animal;
import com.luetolf.beans.Cat;
import com.luetolf.beans.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class AnimalConfig {

    @Bean
    public Animal dog() {
        return new Dog();
    };

    @Bean
    @Primary
    public Animal cat() {
        return new Cat();
    };
}

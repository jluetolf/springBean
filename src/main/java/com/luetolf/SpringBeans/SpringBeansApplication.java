package com.luetolf.SpringBeans;

import com.luetolf.AnimalConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootApplication
@Import({AnimalConfig.class})
public class SpringBeansApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBeansApplication.class, args);
    }

}

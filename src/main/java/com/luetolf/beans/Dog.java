package com.luetolf.beans;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dog implements Animal {

    public static final String noise = "Wauwau";
    public Dog() {
        log.info("Constructor Dog");
    }

    @Override
    public String makeNoise() {
        return noise;
    }

}

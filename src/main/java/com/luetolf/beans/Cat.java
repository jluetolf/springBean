package com.luetolf.beans;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cat implements Animal {
    public static final String noise = "Miauw";
    public Cat() {
        log.info("Constructor Cat");
    }

    @Override
    public String makeNoise() {
        return noise;
    }

}

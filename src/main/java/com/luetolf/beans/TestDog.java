package com.luetolf.beans;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDog implements Animal {

    public static final String noise = "Test Wauwau";
    public TestDog() {
        log.info("Constructor TestDog");
    }

    @Override
    public String makeNoise() {
        return noise;
    }

}

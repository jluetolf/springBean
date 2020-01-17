package com.luetolf.beans;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestCat implements Animal {

    public static final String noise = "Test Miauw";

    public TestCat() {
        log.info("Constructor TestCat");
    }

    public String makeNoise() {
        return noise;
    }

}

package com.kayaspring.kayaspring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KayaspringApplicationTests {
    @Test
    void contextLoads() {
        int expected = 5;
        int actual = calculateSomeValue();
        Assertions.assertEquals(expected, actual, "The calculated value should be 5");
    }


    private int calculateSomeValue() {

        return 5;
    }
}

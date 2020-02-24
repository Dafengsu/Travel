package com.hzau.test;

import org.junit.Test;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class VarArgsDemo {
    public static void test(String... args) {
        System.out.println(args);
    }

    @Test
    public void test1() {
        test();
    }
}

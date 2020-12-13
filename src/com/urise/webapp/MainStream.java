package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {
    private int[] values = {1, 2, 3, 3, 2, 3};
    private int[] values2 = {9, 8};

    List<Integer> integers = new ArrayList<>(Arrays.asList(10, 3, 20));

    public static void main(String[] args) {
        MainStream mainStream = new MainStream();
        System.out.println(mainStream.minValue(mainStream.values));
        System.out.println(mainStream.minValue(mainStream.values2));

        System.out.println(mainStream.oddOrEven(mainStream.integers));
    }


    private int minValue(int[] values) {
        AtomicInteger n = new AtomicInteger(1);
        AtomicInteger num = new AtomicInteger(0);
        Arrays.stream(values).distinct().boxed().sorted(Collections.reverseOrder()).forEach(k -> {
            num.addAndGet(n.get() * k);
            n.updateAndGet(v -> v * 10);
        });
        return num.get();
    }

    private List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0 ?
                integers.stream().filter(n -> n % 2 == 1).collect(Collectors.toList()) :
                integers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }
}

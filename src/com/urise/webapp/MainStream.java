package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    private final int[] values = {1, 2, 3, 3, 2, 3};
    private final int[] values2 = {9, 8};

    List<Integer> integers = new ArrayList<>(Arrays.asList(10, 3, 20, 1));

    public static void main(String[] args) {
        MainStream mainStream = new MainStream();
        System.out.println(mainStream.minValue(mainStream.values));
        System.out.println(mainStream.minValue(mainStream.values2));

        System.out.println(mainStream.oddOrEven(mainStream.integers));
    }


    private int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return integers.stream()
                .filter(n ->  sum % 2 == 0 ? n % 2 == 1 : n % 2 == 0)
                .collect(Collectors.toList());
    }
}

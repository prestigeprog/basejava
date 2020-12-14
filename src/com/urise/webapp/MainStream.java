package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {
    private final int[] values = {1, 2, 3, 3, 2, 3};
    private final int[] values2 = {9, 8};

    List<Integer> integers = new ArrayList<>(Arrays.asList(10, 3, 20));

    public static void main(String[] args) {
        MainStream mainStream = new MainStream();
        System.out.println(mainStream.minValue(mainStream.values));
        System.out.println(mainStream.minValue(mainStream.values2));

        System.out.println(mainStream.oddOrEven(mainStream.integers));
    }


    private int minValue(int[] values) {
        return Arrays.stream(values).distinct().boxed().sorted(Collections.reverseOrder())
                .reduce(0, (a, b) -> (a + b * (int) (a == 0 ? 1 : Math.pow(10, (int) (Math.log10(a) + 1)))));
    }

    private List<Integer> oddOrEven(List<Integer> integers) {
        Supplier<Stream<Integer>> streamSupplier = integers::stream;
        return streamSupplier.get().mapToInt(Integer::intValue).sum() % 2 == 0 ?
                streamSupplier.get().filter(n -> n % 2 == 1).collect(Collectors.toList()) :
                streamSupplier.get().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }
}

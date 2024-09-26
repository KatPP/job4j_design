package ru.job4j.iterator;

import java.util.*;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        while (source.hasNext()) {
            for (ArrayList<Integer> node : nodes) {
                if (source.hasNext()) {
                    node.add(source.next());
                }
            }
        }
    }
}

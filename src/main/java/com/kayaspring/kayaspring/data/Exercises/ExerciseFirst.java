package com.kayaspring.kayaspring.data.Exercises;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFirst {

    public Flux<String> GetNames() {

        List<String> test = new ArrayList<>();
        test.add("ahmet");
        test.add("murat");
        test.add("ferruh");


        var uu = test.stream().filter(x -> x.contains("a")).toList();
        var tt = Flux.fromIterable(uu);
        return tt;
    }

}

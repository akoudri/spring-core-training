package com.akfc.training.reactive;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;

public class FluxGenerate {

    private static Faker faker = new Faker();

    public static void main(String[] args) throws InterruptedException {
        //step 1: tester les différentes méthodes de création de Flux
        //step 2: generate flux of integers using interval and square them
        //step 3: create infinite flux using create; complete the flux after the 17th element; buffer it and display buffers
        Flux.create(sink -> {
                    int i = 0;
                    while (i < 17) {
                        sink.next(faker.name().fullName());
                        i++;
                    }
                    sink.complete();
                })
                .buffer(5)
                .subscribe(System.out::println);
        //step 4: create a stateful infinite buffer using generate to display the first 20 elements of the fibonacci series
        Flux.generate(
                () -> Tuples.of(0L, 1L),
                (state, sink) -> {
                    long a = state.getT1();
                    long b = state.getT2();
                    sink.next(a);
                    return Tuples.of(b, a + b);
                }
        )
                .take(20)
                .subscribe(System.out::println);
    }
}

package com.akfc.training.reactive;

import reactor.core.publisher.Flux;

public class MultiCastFlux {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> f1 = Flux.range(1, 5);
        Flux<Integer> f2 = Flux.range(6, 10);
        //TODO: test the following combinations: concat, merge, zip
        f1.doOnNext(System.out::println).materialize().doOnNext(System.out::println).dematerialize().subscribe(System.out::println);
    }

}

package com.akfc.training.reactive;

import reactor.core.publisher.Flux;

public class FluxEvents {

    public static void main(String[] args) {
        //TODO: test events
        Flux.range(1, 18)
                .collectList()
                .flatMapIterable(e -> e)
                .subscribe(System.out::println);
    }
}

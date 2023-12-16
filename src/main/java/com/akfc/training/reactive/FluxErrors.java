package com.akfc.training.reactive;

import reactor.core.publisher.Flux;

public class FluxErrors {

    public static void main(String[] args) {
        Flux.range(1, 20)
                .handle((e, sink) -> {
                    int v = e * e;
                    if (v == 100) sink.error(new RuntimeException("Forbidden value"));
                    else if (v % 2 == 0) sink.next(v);
                })
                .subscribe(System.out::println, System.err::println, () -> System.out.println("Processing completed"));
    }

}

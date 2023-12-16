package com.akfc.training.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxToMono {

    public static void main(String[] args) {
        Flux f = Flux.range(0, 10);
        //TODO: transform flux to mono
        //1st step: take first element
        Mono m = f.next();
        //2nd step: take all elements as an array
        Mono n = f.collectList();
    }
}

package com.akfc.training.reactive;

import reactor.core.publisher.Flux;

public class FluxToMono {

    public static void main(String[] args) {
        Flux f = Flux.range(0, 10);
        //TODO: transform flux to mono
        //1st step: take first element
        //2nd step: take all elements as an array
    }
}

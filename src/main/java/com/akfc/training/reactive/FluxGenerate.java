package com.akfc.training.reactive;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

public class FluxGenerate {

    private static Faker faker = new Faker();

    public static void main(String[] args) throws InterruptedException {
        //Flux.interval(Duration.ofSeconds(1)).map(e -> e * e).subscribe(System.out::println);
        /*Flux.range(1, 20)
                .handle((e, sink) -> {
                    int v = e * e;
                    if (v == 100) sink.error(new RuntimeException("Forbidden value"));
                    else if (v % 2 == 0) sink.next(v);
                })
                .cast(Double.class)
                .map(Math::sqrt)
                .subscribe(System.out::println, err -> System.err.println("Forbidden value"), () -> System.out.println("Processing completed"));*/
        /*Flux.range(1, 18)
                .collectList()
                .flatMapIterable(e -> e)
                .doOnNext(d -> System.out.println("Processing " + d))
                .doOnComplete(() -> System.out.println("Finished"))
                .subscribe(System.out::println);*/
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

    }
}

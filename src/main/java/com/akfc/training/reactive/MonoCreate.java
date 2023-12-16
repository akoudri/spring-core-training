package com.akfc.training.reactive;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

public class MonoCreate {

    private static Faker faker = new Faker();

    public static String getFullName() {
        System.out.println("Generating name...");
        return faker.name().fullName();
    }

    public static Mono<String> fullName() {
        //Step 1: Create a mono using just and execute
        return Mono.just(getFullName());
        //Step 2: Create a mono using a supplier
        //return Mono.fromSupplier(MonoCreate::getFullName);
    }

    public static void main(String[] args) throws InterruptedException {
        fullName()
                .log()
                .map(String::toUpperCase)
                .log()
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Processing completed"));
    }

}

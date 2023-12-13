package com.akfc.training.reactive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveStreamExercise {

    public static void main(String[] args) {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        TransformProcessor transformer = new TransformProcessor();
        EnsSubscriber subscriber = new EnsSubscriber();
        publisher.subscribe(transformer);
        transformer.subscribe(subscriber);
        for (int i = 0; i < 10; i++) {
            publisher.submit(i);
        }
        publisher.close();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class EnsSubscriber implements Flow.Subscriber<Integer> {

        private Flow.Subscription subscription;
        private final List<Integer> numbers = new ArrayList<>();

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(Integer item) {
            numbers.add(item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.err.println(throwable.getMessage());
            subscription.cancel();
        }

        @Override
        public void onComplete() {
            System.out.println("Received following numbers:");
            numbers.forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }

    static class TransformProcessor extends SubmissionPublisher<Integer> implements Flow.Processor<Integer, Integer> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1); // Requesting the first item
        }

        @Override
        public void onNext(Integer item) {
            Integer result = item * item; // Squaring the item
            submit(result); // Submitting the squared result
            subscription.request(1); // Requesting the next item
        }

        @Override
        public void onError(Throwable throwable) {
            System.err.println(throwable.getMessage());
            subscription.cancel();
        }

        @Override
        public void onComplete() {
            System.out.println("Transformation completed");
            close();
        }
    }

}

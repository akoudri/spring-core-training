package com.akfc.training.reactive;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

class EndSubscriber<T> implements Flow.Subscriber<T> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("End subscriber received data " + item.toString());
        try {
            Thread.sleep(1000);
            subscription.request(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Subscriber done");
    }
}

class TransformerProcessor<T, R> extends SubmissionPublisher<R> implements Flow.Processor<T, R> {

    private Flow.Subscription subscription;
    private Function<T, R> function;

    public TransformerProcessor(Function<T, R> function) {
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit(function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}

public class ReactiveStreamExercise {
    public static void main(String[] argv) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        TransformerProcessor<Integer, Integer> transformerProcessor = new TransformerProcessor<>(e -> e * e);
        EndSubscriber<Integer> subscriber = new EndSubscriber<>();
        publisher.subscribe(transformerProcessor);
        transformerProcessor.subscribe(subscriber);
        for (Integer i : List.of(1, 2, 3, 4, 5)) {
            Thread.sleep(500);
            publisher.submit(i);
        }
        publisher.close();
        Thread.sleep(10000);
    }
}

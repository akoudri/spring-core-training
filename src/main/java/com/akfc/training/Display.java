package com.akfc.training;

public class Display {

    private Hello hello;

    public Display(Hello hello) {
        this.hello = hello;
    }

    public void display() {
        System.out.println(hello.getMessage());
    }

}

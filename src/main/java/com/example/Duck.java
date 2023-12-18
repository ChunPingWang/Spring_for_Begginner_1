package com.example;

public abstract class Duck {
    QuackBehavior quackBehavior;
    FlyBehavior flyBehavior;

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }


    public abstract String display();

    public String performQuack(){
        return quackBehavior.quack();
    }

    public String performFly() {
        return flyBehavior.fly();
    }
}

package com.example;

public abstract class Duck {
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    QuackBehavior quackBehavior;

    public abstract String display();

    public String performQuack(){
        return quackBehavior.quack();
    }
}

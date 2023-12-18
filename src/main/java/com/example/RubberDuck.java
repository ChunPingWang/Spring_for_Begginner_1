package com.example;

public class RubberDuck extends Duck {

   // private FlyBehavior flyBehavior;

    public RubberDuck(){
        flyBehavior = new FlyNoWay();
    }

    @Override
    public String display() {
        return "Rubber Duck";
    }
}

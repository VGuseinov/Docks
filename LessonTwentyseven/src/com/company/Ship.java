package com.company;

import java.util.Random;

public class Ship {

    public int getWeight() {
        return weight;
    }

    private String name;
    private int weight;
    private static int shipNumber = 0;
    private static Random rand = new Random();

    public Ship() {
        this.name = "ship-" + ++shipNumber;
        this.weight = rand.nextInt(9001) + 1000;
    }

    @Override
    public String toString() {
        return "Ship {" +
                "name = '" + name + '\'' +
                ", weight = " + weight +
                '}';
    }


}

package com.company;

import java.util.LinkedList;

public class Tunnel {

    private final LinkedList<Ship> shipList;
    private int maxCapacity;

    public Tunnel(int maxCapacity) {
        this.shipList = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    public void addShip(Ship ship) throws Exception {
        synchronized (this.shipList) {
            if (this.shipList.size() >= this.maxCapacity) {
                throw new Exception("Tunnel is full");
            }
            System.out.println(ship + " Корабль прибыл в туннель");
            this.shipList.add(ship);
        }
    }

    public Ship getShip() {
        synchronized (this.shipList) {
            if (this.shipList.isEmpty()) {
                throw new RuntimeException("Tunnel is empty");
            }
            return this.shipList.removeFirst();
        }
    }

    public boolean isFull() {
        synchronized (this.shipList) {
            return this.shipList.size() >= this.maxCapacity;
        }
    }
}

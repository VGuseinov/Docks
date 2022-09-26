package com.company;

public class Dock {

    private static int dockCounter;
    private int dockNumber;
    private boolean empty = true;

    public boolean isEmpty() {
        return this.empty;
    }

    public synchronized void unloadShip (Ship ship) {
        this.empty = false;
        System.out.println(ship + " Прибыл в док: " + this.dockNumber);
        try {
            Thread.sleep(ship.getWeight());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // сообщаем что dock освободился
        this.notify();
        System.out.println("Док: " + this.dockNumber + " Освободился");
        this.empty = true;
    }

    public Dock() {
        this.dockNumber = ++dockCounter;
    }
}

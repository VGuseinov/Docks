package com.company;

import java.util.LinkedList;

public class Port {

    private LinkedList<Dock> docks;
    private Tunnel tunnel;
    private Thread tunnelThread;

    public Port(int dockCount, int tunnelCapacity) {
        this.tunnel = new Tunnel(tunnelCapacity);
        this.docks = new LinkedList<>();
        for (int i = 0; i < dockCount; i++) {
            this.docks.add(new Dock());
        }
    }

    private void manageTunnel() {
        this.tunnelThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (!this.tunnel.isFull()) {
                    // добавляем корабль в туннель
                    Ship ship = Sea.crateShip();
                    try {
                        this.tunnel.addShip(ship);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.tunnelThread.start();
    }

    public void start() {
        this.manageDocks();
        this.manageTunnel();
        try {
            this.tunnelThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void manageDocks() {
        for (Dock dock : this.docks) {
            Thread t = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        if (dock.isEmpty()) {
                            try {
                                dock.unloadShip(this.tunnel.getShip());
                            } catch (RuntimeException e) {
                             //  System.out.println("Док свободен, но в туннеле не кораблей");
                            }
                            continue;
                        }
                        synchronized (dock) {
                            dock.wait();  // ждем пока док освободится
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }
}

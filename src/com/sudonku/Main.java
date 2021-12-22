package com.sudonku;

public class Main {
    /**
     * A usage example
     */
    public static void main(String[] args) {
        Sudonku sk = new Sudonku(80);
        sk.generateBoard();
        System.out.println("Puzzle:\n"+sk);

        sk.solve();

        System.out.println("Solution:\n"+sk);
    }
}

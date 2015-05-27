package com.kutsyk.main;

import com.kutsyk.upgrader.Upgrader;

public class Main {
    public static void main(String[] args) {
        Upgrader upgrader = new Upgrader(System.getProperty("user.dir"));
        upgrader.upgrade();
    }
}

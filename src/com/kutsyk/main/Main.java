package com.kutsyk.main;

import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.io.IOException;

import com.kutsyk.upgrader.Upgrader;

public class Main {
    public static void main(String[] args) {
        final Upgrader upgrader = new Upgrader(System.getProperty("user.dir"));
        if(upgrader.existNewVersion()) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager
                                .getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                        new MainForm().setVisible(true);
                }
            });
        } else {
            System.out.println("Newest version");
            try {
                Runtime.getRuntime().exec("java -jar " + System.getProperty("user.dir") + "/bin/program.jar");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

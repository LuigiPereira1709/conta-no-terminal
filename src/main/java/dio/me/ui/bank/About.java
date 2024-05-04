package main.java.dio.me.ui.bank;

import java.util.concurrent.TimeUnit;

public class About {
    public void displayInfo() {
        System.out.println("=== About ===");
        System.out.println("This project was developed as a challenge from the Santander Bootcamp Backend in Java.");
        System.out.println("The goal was to test the syntax and concepts presented during the Syntax module.");
        System.out.println("I hope you have a good experience!");
        System.out.println();
        System.out.println("Social Media:");
        System.out.println("LinkedIn: https://www.linkedin.com/in/luigi-pereira-389875296/");
        System.out.println("Email: luigipereira1001@gmail.com");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

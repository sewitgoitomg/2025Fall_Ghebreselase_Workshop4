package org.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String dbUrl = args[0];
        String dbUser = args[1];
        String dbPass = args[2];


        UserInterface ui = new UserInterface(dbUrl, dbUser, dbPass);
        ui.display();




    }
}
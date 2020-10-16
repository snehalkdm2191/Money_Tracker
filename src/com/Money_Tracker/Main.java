package com.Money_Tracker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        UserInterface userInterface = new UserInterface();
        userInterface.balance();
        userInterface.showMenu(0);
    }
}

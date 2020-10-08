package com.Money_Tracker;

import java.util.Scanner;

public class Main {
    static int totalAmount;

    public static void main(String[] args) {

        showMenu(0);
    }

    public static void showMenu(int opt){
        int option = opt;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Track Money");
        System.out.println("You have currently ( " + totalAmount + " ) kr on your account");
        System.out.println("Pick an option");
        System.out.println("(1) Show items(All / Expense / Income)");
        System.out.println("(2) Add new expense / income");
        System.out.println("(3) Edit item (edit / remove)");
        System.out.println("(4) Save and Quite");

    }
}

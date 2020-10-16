package com.Money_Tracker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class UserInterface {
    static double totalAmount;
    static String type;
    static String title;
    static String description;
    static double amount;
    static Integer month;
    static double totalIncome;
    static double totalExpense;

    public static void showMenu(int opt) throws IOException {
        Transactions transactions = new Transactions();
        int option = opt;
        int updateOption = 0;
        int editData;
        String searchTitle;
        int searchMonth;
        Scanner scanner = new Scanner(System.in);

        System.out.println(">> Welcome to Track Money");
        System.out.println(">> You have currently ( " + totalAmount + " ) kr on your account");
        System.out.println(">> Pick an option");
        System.out.println(">> (1) Show items(All / expense / income)");
        System.out.println(">> (2) Add new expense / income");
        System.out.println(">> (3) Edit item (edit / remove)");
        System.out.println(">> (4) Save and Quite");

        do{
            System.out.println("-----------------------------------------------------------------");
            option = scanner.next().charAt(0);

            switch (option){
                case '1':
                    System.out.println("Pick option (1. All / 2. income / 3. expense)");
                    updateOption = scanner.next().charAt(0);
                    switch (updateOption){
                        case '1':
                            transactions.showData("all");
                            break;
                        case '2':
                            transactions.showData("income");
                            break;
                        case'3':
                            transactions.showData("expense");
                            break;
                        default:
                            break;
                    }

                    break;

                case '2':
                    System.out.println("Pick option (1. Income / 2. Expense)");
                    updateOption = scanner.next().charAt(0);
                    switch (updateOption){
                        case '1':
                            type = "Income";
                            break;
                        case '2':
                            type = "Expense";
                            break;
                        default:
                            System.out.println("Invalid Option..");
                            break;
                    }

                    if(type != null) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Enter Title : ");
                        title = scanner.next();
                        System.out.println("Enter Description : ");
                        description = scanner.next();
                        description += scanner.nextLine();
                        System.out.println("Enter Amount : ");
                        amount = scanner.nextDouble();
                        System.out.println("Enter Month : ");
                        month = scanner.nextInt();
                        transactions.addData(type, title, description, amount, month);
                    }
                    else{
                        System.out.println("Transaction type should not be null..");
                    }
                    break;

                case '3':
                    System.out.println("Pick option (1. Edit / 2. Remove)");
                    System.out.println("-----------------------------------------------------------------");
                    updateOption = scanner.next().charAt(0);
                    switch (updateOption) {
                        case '1':
                            System.out.println("Select Edit Option (1. All/ 2. Title/ 3. Description/ 4. Amount/5. Month)");
                            editData = scanner.next().charAt(0);
                            switch (editData) {
                                case '1':
                                    System.out.println("Enter Title & Month(1-12) to search record ");
                                    System.out.println("Enter Title : ");
                                    searchTitle = scanner.next();
                                    System.out.println("Enter Month : ");
                                    searchMonth = scanner.nextInt();
                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.println("Enter data to edit.. ");
                                    System.out.println("Enter Title : ");
                                    title = scanner.next();
                                    System.out.println("Enter Description : ");
                                    description = scanner.next();
                                    description += scanner.nextLine();
                                    System.out.println("Enter Amount : ");
                                    amount = scanner.nextDouble();
                                    System.out.println("Enter Month : ");
                                    month = scanner.nextInt();
                                    transactions.editData(editData, searchTitle, searchMonth, title, month, amount, description);
                                    break;
                                case '2':
                                    System.out.println("Enter Title & Month(1-12) to search record ");
                                    System.out.println("Enter Title : ");
                                    searchTitle = scanner.next();
                                    System.out.println("Enter Month : ");
                                    searchMonth = scanner.nextInt();
                                    System.out.println("-----------------------------------------------------------------");

                                    System.out.println("Enter Title to edit : ");
                                    title = scanner.next();
                                    transactions.editData(editData, searchTitle, searchMonth, title, month, amount, description);
                                    break;
                                case '3':
                                    System.out.println("Enter Title & Month(1-12) to search record ");
                                    System.out.println("Enter Title : ");
                                    searchTitle = scanner.next();
                                    System.out.println("Enter Month : ");
                                    searchMonth = scanner.nextInt();
                                    System.out.println("-----------------------------------------------------------------");

                                    System.out.println("Enter Description to edits : ");
                                    description = scanner.next();
                                    description += scanner.nextLine();
                                    transactions.editData(editData, searchTitle, searchMonth, title, month, amount, description);
                                    break;
                                case '4':
                                    System.out.println("Enter Title & Month(1-12) to search record ");
                                    System.out.println("Enter Title : ");
                                    searchTitle = scanner.next();
                                    System.out.println("Enter Month : ");
                                    searchMonth = scanner.nextInt();
                                    System.out.println("-----------------------------------------------------------------");

                                    System.out.println("Enter Amount to edit : ");
                                    amount = scanner.nextDouble();
                                    transactions.editData(editData, searchTitle, searchMonth, title, month, amount, description);
                                    break;
                                case '5':
                                    System.out.println("Enter Title & Month(1-12) to search record ");
                                    System.out.println("Enter Title : ");
                                    searchTitle = scanner.next();
                                    System.out.println("Enter Month : ");
                                    searchMonth = scanner.nextInt();
                                    System.out.println("-----------------------------------------------------------------");

                                    System.out.println("Enter Month to edit : ");
                                    month = scanner.nextInt();
                                    transactions.editData(editData, searchTitle, searchMonth, title, month, amount, description);
                                    break;
                                default:
                                    System.out.println("Invalid option ! Please enter again");
                                    break;
                            }
                            break;

                        case '2':
                            System.out.println("remove");
                            break;
                    }
                    break;

                default:
                    System.out.println("Invalid option ! Please enter again");
                    break;
            }
        }
        while(option != '4');
        System.out.println("Thank you for using our service");

    }

    public static void balance(){
        JSONParser jsonParser = new JSONParser();
        BufferedReader bufferedReader = null;
        Object showObj;
        try {
            String sCurrentLine; // Variable to check current line

            bufferedReader = new BufferedReader(new FileReader("myJSON.json"));
            //Read the file till current line will be null
            while ((sCurrentLine = bufferedReader.readLine()) != null) {

                showObj = jsonParser.parse(sCurrentLine); // Object to parse current line data
                JSONObject incomeObject = null; // Object for income type transaction data
                JSONObject expenseObject = null; // Object for expense type transaction data

                    incomeObject = (JSONObject) ((JSONObject) showObj).get("Income");
                    expenseObject = (JSONObject) ((JSONObject) showObj).get("Expense");
                    if(incomeObject != null){
                        amount = (Double) incomeObject.get("Amount");
                        totalIncome =  totalIncome + amount;
                    }
                    else if(expenseObject != null){
                        amount = (Double) expenseObject.get("Amount");
                        totalExpense =  totalExpense + amount;
                    }
            }

            totalAmount = totalIncome - totalExpense;

        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (ParseException e) { e.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }
        finally {
            try{
                if(bufferedReader!=null)
                    bufferedReader.close();
            }catch(Exception ex){
                System.out.println("Error in closing the Buffer Reader"+ex);
            }
        }
    }

}

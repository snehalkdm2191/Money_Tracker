package MoneyTracker;

import java.io.*;
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
        balance();
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
                    System.out.println("Pick option (1. All / 2. Income / 3. Expense)");
                    updateOption = scanner.next().charAt(0);
                    switch (updateOption){
                        case '1':
                            transactions.showData("All");
                            break;
                        case '2':
                            transactions.showData("Income");
                            break;
                        case'3':
                            transactions.showData("Expense");
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
                            if(month > 12 || month < 1){
                                System.out.println("Invalid month entry..");
                            }
                            else {
                                transactions.addData(type, title, description, amount, month);
                            }
                            break;
                        case '2':
                            type = "Expense";
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
                            if(month > 12 || month < 1){
                                System.out.println("Invalid month entry..");
                            }
                            else {
                                transactions.addData(type, title, description, amount, month);
                            }
                            break;
                        default:
                            System.out.println("Invalid Option..");
                            break;
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
                                    if(month > 12 || month < 1){
                                        System.out.println("Invalid month entry..");
                                    }
                                    else {
                                        transactions.editData(editData, searchTitle, searchMonth, title, month, amount, description);
                                    }
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
                                    if(month > 12 || month < 1){
                                        System.out.println("Invalid month entry..");
                                    }
                                    else {
                                        transactions.editData(editData, searchTitle, searchMonth, title, month, amount, description);
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid option ! Please enter again");
                                    break;
                            }
                            break;

                        case '2':
                            System.out.println("remove");
                            System.out.println("Enter Title & Month(1-12) to remove ");
                            System.out.println("Enter Title : ");
                            searchTitle = scanner.next();
                            System.out.println("Enter Month : ");
                            searchMonth = scanner.nextInt();
                            System.out.println("-----------------------------------------------------------------");

                            transactions.removeData(searchTitle,searchMonth);
                            break;
                    }
                    break;

                default:
                    if(option != '4')
                        System.out.println("Invalid option ! Please enter again");
                    break;
            }
        }
        while(option != '4');
        System.out.println("Thank you for using our service");

    }

    public static void balance() throws IOException {
        File file= new File("transactionData.txt");
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine())
        {
            String currentLine= sc.nextLine();

            String[] tokens = currentLine.split(" , ");
            if (String.valueOf(tokens[0]).equals("Income")) {
                totalIncome = Double.valueOf(tokens[3]) + totalIncome;
            }
            else if (String.valueOf(tokens[0]).equals("Expense")) {
                totalExpense = Double.valueOf(tokens[3]) + totalExpense;
            }

            totalAmount = totalIncome - totalExpense;

        }
        sc.close();
    }

}

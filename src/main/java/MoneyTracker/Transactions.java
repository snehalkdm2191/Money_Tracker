package MoneyTracker;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Transactions {

    //Method to add all type of transactions in text file
    public void addData(String transType, String title, String description, Double amount, Integer month) throws IOException {
        File file = new File("transactionData.txt");

        ArrayList list = new ArrayList();
        list.add(transType);
        list.add(title);
        list.add(description);
        list.add(amount);
        list.add(month);

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            Writer output = new BufferedWriter(fileWriter);
            output.write("\n");
            int size = list.size();
            for(int i=0; i < size; i++){
                if(i==0)
                    output.write(list.get(i).toString());
                else
                    output.write(" , " + list.get(i).toString());
            }
            output.close();
            System.out.println("Data added successfully..");
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            try{
                if(fileWriter!=null)
                    fileWriter.close();
            }catch(Exception ex){
                System.out.println("Error in closing the FileWriter "+ex);
            }
        }
    }

    // Method to show data as per type of transaction
    public void showData(String typeData) throws FileNotFoundException {
        File file= new File("transactionData.txt");
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine())
        {
            String currentLine= sc.nextLine();

            String[] tokens = currentLine.split(" , ");
            if (String.valueOf(tokens[0]).equals("Income") && (typeData.equals("Income") || typeData.equals("All"))) {
                System.out.println("Transaction type : " + tokens[0]);
                System.out.println("Title : " + tokens[1]);
                System.out.println("Description : " + tokens[2]);
                System.out.println("Amount : " + tokens[3]);
                System.out.println("Month : " + tokens[4]);
                System.out.println("-----------------------------------------------------------------");
            }
            else if (String.valueOf(tokens[0]).equals("Expense") && (typeData.equals("Expense")|| typeData.equals("All"))) {
                System.out.println("Transaction type : " + tokens[0]);
                System.out.println("Title : " + tokens[1]);
                System.out.println("Description : " + tokens[2]);
                System.out.println("Amount : " + tokens[3]);
                System.out.println("Month : " + tokens[4]);
                System.out.println("-----------------------------------------------------------------");
            }

        }
        sc.close();
    }

    // Method to edit json file data
    public void editData(int editOption,String searchTitle,int searchMonth,String editTitle,Integer editMonth,Double editAmount,String editDescription) throws IOException {
        boolean checked = true; // variable created to check if record is present or not.
        BufferedWriter writer = null;
        File file= new File("transactionData.txt");  //Original file path
        File tempFile = new File("tempFile.txt");   //Temparary file path

        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
            Scanner sc = new Scanner(file); // Read original file

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();  // Convert current line to string to split data
                String[] tokens = currentLine.split(" , ");

                // if record is present for provided month then it will edit the record
                if (String.valueOf(tokens[1]).equals(searchTitle) && Integer.valueOf(tokens[4]) == searchMonth && checked) {
                    switch (editOption) {
                        case '1':
                            currentLine = tokens[0] + " , " + editTitle + " , " + editDescription + " , " + editAmount + " , " + editMonth;
                            checked = false;
                            break;
                        case '2':
                            currentLine = tokens[0] + " , " + editTitle + " , " + tokens[2] + " , " + tokens[3] + " , " + tokens[4];
                            checked = false;
                            break;
                        case '3':
                            currentLine = tokens[0] + " , " + tokens[1] + " , " + editDescription + " , " + tokens[3] + " , " + tokens[4];
                            checked = false;
                            break;
                        case '4':
                            currentLine = tokens[0] + " , " + tokens[1] + " , " + tokens[2] + " , " + editAmount + " , " + tokens[4];
                            checked = false;
                            break;
                        case '5':
                            currentLine = tokens[0] + " , " + tokens[1] + " , " + tokens[2] + " , " + tokens[3] + " , " + editMonth;
                            checked = false;
                            break;
                        default:
                            System.out.println("Invalid option..");
                            break;
                    }
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            if (checked == true) {
                System.out.println("No matching record..");
            } else {
                System.out.println("Data edited successfully..");
            }
            writer.close();
            sc.close();
            file.delete(); //after data added in temp file this statement will delete original file.
            boolean successful = tempFile.renameTo(file); //after successful editing this statement will rename temparary file to original file name
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            try{
                if(writer!=null)
                    writer.close();
            }catch(Exception ex){
                System.out.println("Error in closing the FileWriter "+ex);
            }
        }
    }

    //Method created to remove data
    public void removeData(String searchTitle,int searchMonth) throws IOException {
        boolean checked = true;   // check change has been done or not
        BufferedWriter writer = null;
        File file= new File("transactionData.txt");      //Original file path
        File tempFile = new File("tempFile.txt");     //Temparary file path

        try{
        writer = new BufferedWriter(new FileWriter(tempFile));
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine())
        {
            String currentLine= sc.nextLine();      // original file data added in currentLine string

            String[] tokens = currentLine.split(" , ");
            if (String.valueOf(tokens[1]).equals(searchTitle) && Integer.valueOf(tokens[4]) == searchMonth && checked) {
                currentLine = "";    //if record found do changes in that line
                checked = false;
            }

            writer.write(currentLine + System.getProperty("line.separator"));       // change added in temparary file

        }
        writer.close();
        sc.close();
        file.delete();      // delete original file
        boolean successful = tempFile.renameTo(file);   // rename temparary file to original file name
            if(successful == true){
                System.out.println("Data removed successfully..");
            }
            else {
                System.out.println("No matching record..");
            }
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
        finally {
            try{
                if(writer!=null)
                    writer.close();
            }catch(Exception ex){
                System.out.println("Error in closing the FileWriter "+ex);
            }
        }
    }

}

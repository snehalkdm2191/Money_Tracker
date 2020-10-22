import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class TransactionsTest {

    @Test
    void addData() throws IOException {
        boolean check = false;
        File file = new File("transactionTest.txt");
        ArrayList list = new ArrayList();
        list.add("Income");
        list.add("title");
        list.add("description");
        list.add(100.0);
        list.add(2);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            Writer output = new BufferedWriter(fileWriter);
            int size = list.size();
            for(int i=0; i < size; i++){
                if(i==0)
                    output.write(list.get(i).toString());
                else
                    output.write(" , " + list.get(i).toString());
            }
            if(output != null){
                check = true;
            }
            output.close();
            assertTrue(check);
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
    }

    @Test
    void showData() throws FileNotFoundException {
        File file= new File("transactionTest.txt");
        Scanner sc = new Scanner(file);
        String typeData = "Income";
        boolean check = false;

        while(sc.hasNextLine())
        {
            String currentLine= sc.nextLine();

            String[] tokens = currentLine.split(" , ");
            if (String.valueOf(tokens[0]).equals("Income") && (typeData.equals("Income") || typeData.equals("All"))) {
                if(tokens[1].equals("title"))
                    check = true;
            }
            else if (String.valueOf(tokens[0]).equals("Expense") && (typeData.equals("Expense")|| typeData.equals("All"))) {
                if(tokens[1].equals("title"))
                    check = false;
            }
        }
        assertTrue(check);
    }

    @Test
    void editData() throws IOException {
        boolean checked = false; // variable created to check if record is present or not.
        BufferedWriter writer = null;
        File file= new File("transactionTest.txt");  //Original file path
        File tempFile = new File("tempFile.txt");   //Temparary file path

        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
            Scanner sc = new Scanner(file); // Read original file

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();  // Convert current line to string to split data
                String[] tokens = currentLine.split(" , ");

                // if record is present for provided month then it will edit the record
                if (String.valueOf(tokens[1]).equals("title") && Integer.valueOf(tokens[4]) == 2) {
                    currentLine = tokens[0] + " , " + "New title" + " , " + tokens[2] + " , " + tokens[3] + " , " + tokens[4];
                    checked = true;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            sc.close();
            file.delete(); //after data added in temp file this statement will delete original file.
            boolean successful = tempFile.renameTo(file); //after successful editing this statement will rename temparary file to original file name
            assertTrue(successful);
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
    }

    @Test
    void removeData() throws IOException {
        boolean checked = false;
        BufferedWriter writer = null;
        File file= new File("transactionTest.txt");      //Original file path
        File tempFile = new File("tempFile.txt");     //Temparary file path

        try{
            writer = new BufferedWriter(new FileWriter(tempFile));
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine())
            {
                String currentLine= sc.nextLine();

                String[] tokens = currentLine.split(" , ");
                if (String.valueOf(tokens[1]).equals("New title") && Integer.valueOf(tokens[4]) == 2) {
                    currentLine = "";
                    checked = true;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            sc.close();
            file.delete();
            boolean successful = tempFile.renameTo(file);
            assertTrue(successful);
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
    }

    @Test
    void removeTestFiles() {
        File file = new File("transactionTest.txt");
        boolean check = file.delete();
        File tempFile = new File("tempFile.txt");
        boolean exists = tempFile.exists();
        if(exists == true)
            tempFile.delete();

        assertTrue(check);
    }
}
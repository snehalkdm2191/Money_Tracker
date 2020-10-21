package test;

import com.MoneyTracker.Transactions;
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
        ArrayList alist = new ArrayList();
        alist.add("transType");
        alist.add("title");
        alist.add("description");
        alist.add(100.0);
        alist.add(2);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            Writer output = new BufferedWriter(fileWriter);
            output.write("\n");
            int size = alist.size();
            for(int i=0; i < size; i++){
                if(i==0)
                    output.write(alist.get(i).toString());
                else
                    output.write(" , " + alist.get(i).toString());
            }
            if(output != null){
                check = true;
            }
            output.close();
            assertEquals(true, check);
        }
        finally {
            file.delete();
        }
    }

    @Test
    void showData() {
    }

    @Test
    void editData() throws IOException {
        boolean checked = true; // variable created to check if record is present or not.
        BufferedWriter writer = null;
        File file= new File("transactionTest.txt");  //Original file path

        try {
            writer = new BufferedWriter(new FileWriter(file));
            Scanner sc = new Scanner(file); // Read original file

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();  // Convert current line to string to split data
                String[] tokens = currentLine.split(" , ");

                // if record is present for provided month then it will edit the record
                if (String.valueOf(tokens[1]).equals("title") && Integer.valueOf(tokens[4]) == 4 && checked) {
                       currentLine = tokens[0] + " , " + tokens[1] + " , " + tokens[2] + " , " + tokens[3] + " , " + tokens[4];
                       checked = false;
                       break;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            assertEquals(true, checked);
            writer.close();
            sc.close();
        }
        finally {
            file.delete();
        }
    }

    @Test
    void removeData() {

    }
}
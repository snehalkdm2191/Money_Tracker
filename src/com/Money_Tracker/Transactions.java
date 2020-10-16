package com.Money_Tracker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Transactions {
    public String type;
    public String title;
    public String description;
    private double amount;
    private Integer month;

    //Method to add all type of transactions in json file
    public void addData(String transType, String title, String description, Double amount, Integer month) throws IOException {
        JSONObject obj = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("Type", transType);
        data.put("Title", title);
        data.put("Description", description);
        data.put("Amount", amount);
        data.put("Month", month);

        if(transType.equals("Income")) { // if input data is of income transaction type then it will create list for income
            obj.put("Income", data);
        }
        else{
            obj.put("Expense", data); // if input data is of expense transaction type then it will create list for income
        }

        JSONArray incomeList = new JSONArray();
        incomeList.add(data);

        FileWriter file = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File("myJson.JSON"),true));
            bufferedReader = new BufferedReader(new FileReader("myJSON.json"));
            if(bufferedReader.readLine() != null) {
                bufferedWriter.newLine();
                bufferedWriter.write(obj.toJSONString());
            }
            else {
                bufferedWriter.write(obj.toJSONString());
            }
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            try{
                if(bufferedWriter!=null)
                    bufferedWriter.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }

        System.out.println(obj);
    }

    //Created method showTransaction to show transaction wise data.
    public void showTransaction(JSONObject jsonObject){
        if(jsonObject != null) {
            type = (String) jsonObject.get("Type");
            System.out.println("Transaction type : " + type);

            title = (String) jsonObject.get("Title");
            System.out.println("Title : " + title);

            description = (String) jsonObject.get("Description");
            System.out.println("Description : " + description);

            amount = (Double) jsonObject.get("Amount");
            System.out.println("Amount : " + amount);

            month = ((Long) jsonObject.get("Month")).intValue();
            System.out.println("Month : " + month);
            System.out.println("-----------------------------------------------------------------");
        }
    }

    // Method to show data as per type of transaction
    public void showData(String typeData){
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

                //Get data as per transaction type
                if(typeData.equals("income")){
                    incomeObject = (JSONObject) ((JSONObject) showObj).get("Income");
                    showTransaction(incomeObject);
                }
                else if(typeData.equals("expense")){
                    expenseObject = (JSONObject) ((JSONObject) showObj).get("Expense");
                    showTransaction(expenseObject);
                }
                else{
                    incomeObject = (JSONObject) ((JSONObject) showObj).get("Income");
                    expenseObject = (JSONObject) ((JSONObject) showObj).get("Expense");
                    if(incomeObject != null){
                        showTransaction(incomeObject);
                    }
                    else {
                        showTransaction(expenseObject);
                    }
                }
            }

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

    // Method to edit json file data
    public void editData(int editOption,String searchTitle,int searchMonth,String editTitle,Integer editMonth,Double editAmount,String editDescription){
            JSONParser jsonParser = new JSONParser();
            BufferedReader bufferedReader = null;
            Object editObj;
            try {
                String sCurrentLine;

                //Read the file till current line will be null
                bufferedReader = new BufferedReader(new FileReader("myJSON.json"));
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    System.out.println("Record:\t" + sCurrentLine);

                    editObj = jsonParser.parse(sCurrentLine);
                    JSONObject jsonObject = null;
                    JSONObject incomeObject = null; // Object for income type transaction data
                    JSONObject expenseObject = null; // Object for expense type transaction data

                    //JSONObject jsonObject = (JSONObject) ((JSONObject) editObj).get("Transaction Data");
                    incomeObject = (JSONObject) ((JSONObject) editObj).get("Income");
                    expenseObject = (JSONObject) ((JSONObject) editObj).get("Expense");
                    if(incomeObject != null){
                        jsonObject = incomeObject;
                    }
                    else {
                        jsonObject = expenseObject;
                    }

                    if(jsonObject != null) {
                        type = (String) jsonObject.get("Type");
                        title = (String) jsonObject.get("Title");
                        month = ((Long) jsonObject.get("Month")).intValue();

                        //Search type of transaction with title in particular month.
                        if (searchTitle.equals(title) && searchMonth == month) {
                            switch (editOption) {
                                case '1':
                                    jsonObject.put("Title", editTitle);
                                    jsonObject.put("Description", editDescription);
                                    jsonObject.put("Amount", editAmount);
                                    jsonObject.put("Month", editMonth);
                                    break;
                                case '2':
                                    jsonObject.put("Title", editTitle);
                                    break;
                                case '3':
                                    jsonObject.put("Description", editDescription);
                                    break;
                                case '4':
                                    jsonObject.put("Amount", editAmount);
                                    break;
                                case '5':
                                    jsonObject.put("Month", editMonth);
                                    break;
                                default:
                                    System.out.println("Invalid option..");
                                    break;
                            }

                            BufferedWriter bufferedWriter = null;
                            try {
                                // created bufferedWriter to update file
                                bufferedWriter = new BufferedWriter(new FileWriter(new File("myJson.JSON"), true));
                                bufferedWriter.newLine();
                                JSONObject newJsonObject = new JSONObject();
                                newJsonObject.put(type, jsonObject);
                                bufferedWriter.write(newJsonObject.toJSONString());

                            } catch (FileNotFoundException e) {
                                System.out.println("File not found");
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (bufferedWriter != null)
                                        bufferedWriter.close();
                                } catch (Exception ex) {
                                    System.out.println("Error in closing the BufferedWriter" + ex);
                                }
                            }
                            break;
                        } else {
                            System.out.println("There is no such entry from month " + editMonth);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}

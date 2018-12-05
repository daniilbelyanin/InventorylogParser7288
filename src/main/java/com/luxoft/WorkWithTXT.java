package com.luxoft;

import java.io.*;
import java.util.ArrayList;

public class WorkWithTXT {

    private String inputFilename, outputFilename;

    public ArrayList<String> textFile2ArrayList() {
        ArrayList<String> log = new ArrayList<String>();
        String line;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename), "cp1251"))) {
            try {
                while ((line = in.readLine()) != null) {
                    log.add(line);
                }
            } catch (IOException e) {
                System.out.println("Input\\output exception!");
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported file encoding! ");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! ");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Input\\output exception!");
            e.printStackTrace();
        }
        return log;
    }

    public void string2TextFile(String text) {
        try{
            FileWriter fstream = new FileWriter(outputFilename,true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(text);
            out.close();
        }catch (Exception e){
            System.err.println("Error while writing to file: " +
                    e.getMessage());
        }
    }

    public void setInputFilename(String filename) {
        inputFilename = filename;
    }

    public void setOutputFilename(String filename) {
        outputFilename = filename;
    }

}

package com.luxoft;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogParser {

    public void string2TextFile(String text, String outputFilename) {
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

    public void process(String inputFolder, String outputFile) throws IOException {

        ResponseProcessor responseProcessor = new ResponseProcessor();
        ArrayList<String> files = new ArrayList<String>();
        WorkWithFolder worker = new WorkWithFolder();
        worker.setFolder(inputFolder);
        files=worker.returnFilesList();

        for (int i=0; i<files.size(); i++) {

            File theFile = new File(files.get(i));
            LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
            try {
                while (it.hasNext()) {
                    String line = it.nextLine();
                    if (line.contains("ServiceRequestCommand") && line.contains("matlock")) {
                        responseProcessor.setResponse(line);
                        responseProcessor.processResponse();
                        string2TextFile(responseProcessor.printResponse(";"), outputFile);
                    }
                }
            } finally {
                LineIterator.closeQuietly(it);
            }
        }
    }
}

package com.luxoft;

public class EntryPoint {
    public static void main(String args[]) {
        WorkWithTXT workertxt = new WorkWithTXT();
        workertxt.setOutputFilename("log_output.csv");
        WorkWithRequest worker = new WorkWithRequest();
        worker.readLogFile("log.txt");
        while(worker.getRequest()==true) {
            workertxt.string2TextFile(worker.getOperation() + " - " + worker.getMaterialsCount() + "," + worker.returnTime() + "," + worker.getSessionKey() + "\n");
        }
    }
}

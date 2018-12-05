package com.luxoft;

import java.util.ArrayList;

public class WorkWithRequest {

    private ArrayList<String> log = new ArrayList<>();
    private ArrayList<String> request = new ArrayList<>();

    public void getLog(ArrayList<String> log) {
        this.log=log;
    }

    public void readLogFile(String logfilename) {
        WorkWithTXT worker = new WorkWithTXT();
        worker.setInputFilename(logfilename);
        getLog(worker.textFile2ArrayList());
    }

    public boolean getRequest() {
        request.clear();
        int beginIndex=0, endIndex=0;
        RegExpWorker regexp = new RegExpWorker();
        while (log.get(beginIndex).contains("requestHeader")==false && beginIndex<log.size()-1) beginIndex++;
        if (beginIndex!=log.size()-1) {
            //while (log.get(endIndex).length() != 43) endIndex++;
            //Получаем реквест
            for (int i = beginIndex; i <= beginIndex + 8; i++) {
                request.add(log.get(i));
            }
            //Чистим лог от реквестов
            for (int j=0; j<=beginIndex; j++) log.remove(0);
            return true;
        }
        else return false;
    }

    public String getOperation() {
        return request.get(1).substring(request.get(1).indexOf("[")+1, request.get(1).indexOf("]"));
    }

    private void verifyRequest() {
    }

    public String getSessionKey () {
        return (request.get(request.size()-1)).substring(3);
    }

    public int getMaterialsCount() {
        RegExpWorker regexp = new RegExpWorker();
        regexp.setRegExp("[\\d]+");
        return regexp.count(request.get(6));
    }

    public String returnTime() {
        String timeString;
        int i=0;
        int j=0;
        while ((log.get(i).contains("ServiceRequestCommand")==false || log.get(i).contains(getSessionKey())==false) && i<log.size()-1) {
            i++;
        }
        if (i!=log.size()-1) {
            timeString = log.get(i);
            //Чистим лог от времени, дабы уменьшить размер
            log.remove(i);
            return timeString.substring(timeString.indexOf("Command - ") + 10, timeString.indexOf(" ms "));
        }
        else return "0";
    }

}

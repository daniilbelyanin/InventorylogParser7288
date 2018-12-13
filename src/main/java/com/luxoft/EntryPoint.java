package com.luxoft;

import java.io.IOException;

public class EntryPoint {
    public static void main(String args[]) throws IOException {
        String inputfolder="", outputfile="default.csv", service="none";

        //defining cmd args
        WorkWithArguments arguments = new WorkWithArguments();
        arguments.setSimpleArgument("h", "help", "shows help");
        arguments.setComplexArgument("i", "inputfolder", "defines input folder with logs (i.e. C:\\Folder)");
        arguments.setComplexArgument("o", "outputfile", "defines output file (i.e. C:\\Folder\\Output.txt)");
        arguments.setComplexArgument("s", "service", "defines service");
        arguments.setCmdArguments(args);

        if (arguments.checkOption("i") & arguments.returnArgValue("i") != null)
            inputfolder = arguments.returnArgValue("i");

        if (arguments.checkOption("o") & arguments.returnArgValue("o") != null)
            outputfile = arguments.returnArgValue("o");

        if (arguments.checkOption("s") & arguments.returnArgValue("s") != null)
            service = arguments.returnArgValue("s");

        LogParser logParser = new LogParser();
        logParser.process(inputfolder, outputfile, service);
    }
}

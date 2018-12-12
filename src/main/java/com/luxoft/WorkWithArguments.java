package com.luxoft;

import org.apache.commons.cli.*;

public class WorkWithArguments {

    private Options options = new Options();
    private HelpFormatter formatter = new HelpFormatter();
    private CommandLineParser parser = new DefaultParser();
    private String[] args;
    private CommandLine cmd = null;

    public void setCmdArguments(String[] args) {
        this.args = args;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean checkValidArgs() {
        if (cmd != null) {
            if (cmd.getArgs().length == 0) return true;
            else return false;
        } else return false;
    }

    public boolean checkOption(String argument) {
        if (cmd == null) return false;
        else return (cmd.hasOption(argument));
    }

    public String returnArgValue(String argument) {
        if (cmd != null) return cmd.getOptionValue(argument);
        else return "none";
    }

    public void setSimpleArgument(String shortname, String longname, String description) {
        options.addOption(shortname, longname, false, description);
    }

    public void setComplexArgument(String shortname, String longname, String description) {
        Option option = Option.builder()
                .longOpt(shortname)
                .argName(longname)
                .hasArg()
                .desc(description)
                .build();
        options.addOption(option);
    }

    public void showHelp() {
        formatter.printHelp("Arguments", options);
    }

}

package com.luxoft;

import java.io.File;
import java.util.ArrayList;

public class WorkWithFolder {

    private String userfolder;
    private ArrayList<String> filesList = new ArrayList<String>();

    public void setFolder(String folder) {
        userfolder=folder;
    }

    private void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if(!fileEntry.getName().contains("jar")) filesList.add(folder + "\\" + fileEntry.getName());
            }
        }
    }

    public ArrayList<String> returnFilesList() {
        final File folder = new File(userfolder);
        listFilesForFolder(folder);
        return filesList;
    }
}

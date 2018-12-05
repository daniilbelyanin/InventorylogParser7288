package com.luxoft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpWorker {

    private Pattern pattern;
    private Matcher matcher;

    public boolean ifContain(String str) {
        try {
            matcher = pattern.matcher(str);
        } catch (NullPointerException e) {
            System.out.println("Error while matching!");
            e.printStackTrace();
        }
        return matcher.find();
    }

    //Задаем регулярное выражение
    public void setRegExp(String regexp) {
        try {
            pattern = Pattern.compile(regexp);
        } catch (NullPointerException e) {
            System.out.println("Regular expression is null!");
            e.printStackTrace();
        }
    }

    public int count(String str) {
        matcher=pattern.matcher(str);
        int count=0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

}

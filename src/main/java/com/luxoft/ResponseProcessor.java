package com.luxoft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseProcessor {

    private String positionsCount, sessionIdKey, responseAnswerTime, responseAnswerDate, responseTime, response;

    private Pattern pattern;
    private Matcher matcher;

    private void setPositionsCount() {
        pattern=Pattern.compile("ms\\s\\d*");
        matcher=pattern.matcher(response);
        matcher.find();
        positionsCount=matcher.group(0);
        positionsCount = positionsCount.replace("ms ", "");
    }

    private void setSessionIdKey() {
        pattern=Pattern.compile(".{40}$");
        matcher=pattern.matcher(response);
        if (matcher.find()) sessionIdKey=matcher.group(0);
    }

    private void setResponseAnswerTime() {
        pattern=Pattern.compile("\\d*:\\d*:\\d*,\\d*");
        matcher=pattern.matcher(response);
        matcher.find();
        responseTime=matcher.group(0);
    }

    private void setResponseAnswerDate() {
        pattern=Pattern.compile("\\d*-\\d*-\\d*");
        matcher=pattern.matcher(response);
        matcher.find();
        responseAnswerDate=matcher.group(0);
    }

    private void setResponseTime() {
        pattern=Pattern.compile("\\)\\s.*ms");
        matcher=pattern.matcher(response);
        matcher.find();
        responseAnswerTime=matcher.group(0);
        responseAnswerTime = responseAnswerTime.replaceAll("\\sms", "");
        responseAnswerTime = responseAnswerTime.replaceAll("\\)\\s", "");
    }

    public void setResponse(String response) {
        this.response=response;
    }

    public void processResponse() {
        setPositionsCount();
        setResponseAnswerDate();
        setResponseAnswerTime();
        setSessionIdKey();
        setResponseTime();
    }

    public String printResponse(String sep) {
        return (responseAnswerDate + sep + responseTime + sep + positionsCount + sep + responseAnswerTime + sep + sessionIdKey);
    }

}

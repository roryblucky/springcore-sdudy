package com.rory.springcore.study;

public class HelloServiceImpl implements HelloService {

    private String text;
    private OutputService outputService;

    public String getText() {
        return text;
    }

    public OutputService getOutputService() {
        return outputService;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    public void helloWorld() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }
}

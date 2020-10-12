package main.Models;


import java.util.ArrayList;

enum RequestName{
    REGISTER,
    ARRIVE,
    DEPART,
    INFO,
    BORROW,
    BORROWED,
    RETURN,
    PAY,
    SEARCH,
    BUY,
    ADVANCE,
    DATETIME,
    REPORT
}

public class Request {

    RequestName name;
    ArrayList<String> parameters;

    public Request(RequestName name, ArrayList<String> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public RequestName getName() {
        return name;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }
}

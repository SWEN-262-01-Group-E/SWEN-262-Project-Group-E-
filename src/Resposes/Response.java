package Resposes;

import Requests.RequestNames;

import java.util.ArrayList;

public abstract class Response {

    RequestNames.RequestName command = RequestNames.RequestName.PARTIAL_REQUEST;
    ArrayList<String> parameters;

    public RequestNames.RequestName getCommand(){
        return this.command;
    };
    public ArrayList<String> getParameters(){
        return this.parameters;
    }
    public String toString(){
        return(command.toString() + " " + parameters.toString());
    };
}

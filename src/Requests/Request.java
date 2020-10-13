package Requests;


import Resposes.Response;
import main.Models.OwningLibrary;

import java.util.ArrayList;

public abstract class Request {

    RequestNames.RequestName command = RequestNames.RequestName.PARTIAL_REQUEST;
    Response response;
    ArrayList<String> parameters;

    /**
     * Constructor for a new Request
     * @param commandString The String version of a command, to be parsed into a CommandName
     * @param parameters The String of Prarmaters
     */
    public Request(String commandString, ArrayList<String> parameters) {
        this.command = setcommand(commandString);
        this.parameters = parameters;
    }

    /**
     * returns the type of request
     * @return the type of request
     */
    public RequestNames.RequestName getRequestName(){
        return this.command;
    }

    public ArrayList<String> getParameters(){
        return this.parameters;
    }

    /**
    public Response ProcessRequest(OwningLibrary libray){
        return(new Response() {
            @Override
            public RequestNames.RequestName getCommand() {
                return null;
            }

            @Override
            public ArrayList<String> getParameters() {
                return null;
            }
        }
    }*/

    private RequestNames.RequestName setcommand(String commandString){
        return RequestNames.RequestName.PARTIAL_REQUEST;
    }


}

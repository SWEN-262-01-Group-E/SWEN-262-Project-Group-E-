package Requests;

import Resposes.Response;
import main.Models.OwningLibrary;

import java.util.ArrayList;

public class RegisterRequest extends Request {


    /**
     * Constructor for a new Request
     *
     * @param commandString The String version of a command, to be parsed into a CommandName
     * @param parameters    The String of Prarmaters
     */
    public RegisterRequest(String commandString, ArrayList<String> parameters) {
        super(commandString, parameters);
    }


}

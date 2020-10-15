package Resposes;

import Requests.RequestNames;
import main.Models.Visit;

public class ArrivalResponse implements Response {

    private static final RequestNames.RequestName COMMAND = RequestNames.RequestName.ARRIVE;

    private String responseMessage;
    /**
     * constructor for the case that the visitor id entered in the request was invalid
     * @param invalidID : the invalid ID
     */
    public ArrivalResponse(String invalidID){
        responseMessage = invalidID;
    }

    /**
     * constructor for the case that the visitor entered was a duplicate
     */
    public ArrivalResponse(){
        responseMessage = "duplicate";
    }

    /**
     * constructor for the case that the visit was successful
     * @param newVisit the new visit started
     */
    public ArrivalResponse(Visit newVisit){
        responseMessage = "invalid ID: " + newVisit.toString();
    }

    @Override
    public RequestNames.RequestName getCommand() {
        return COMMAND;
    }

    @Override
    public String getResponse() {
        return (COMMAND + ", " + responseMessage);
    }
}

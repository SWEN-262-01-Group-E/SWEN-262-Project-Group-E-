package Requests;

import Resposes.ArrivalResponse;
import Resposes.Response;
import main.Models.OwningLibrary;
import main.Models.Visit;

import java.util.ArrayList;

public class ArriveRequest implements Request {

   private static final RequestNames.RequestName COMMAND = RequestNames.RequestName.ARRIVE;
   Long visitorID;
   String invalidID;
   OwningLibrary library;

   public ArriveRequest(OwningLibrary library, ArrayList<String> parameters){

       String stringID = parameters.get(1);

       this.library = library;
       if(Validator.validateAndParseLong(stringID) == -1){
           invalidID = stringID;
       }
       visitorID = Validator.validateAndParseLong(parameters.get(1));
   }

    @Override
    /**
     * returns a response appropriate for the parameters entered in
     */
    public Response performRequest() {
        //if the ID entered was invalid, retruns the invalid ID
       if(visitorID ==-1){
            return new ArrivalResponse(invalidID);
       }

       Visit tempVisit = library.startVisit(visitorID);

       //if the visitor is already visiting, inform the user that it's a duplicate
       if(tempVisit == null){
           return new ArrivalResponse();
       }

       //otherwise show the user the new visit information
       return new ArrivalResponse(tempVisit);
    }
}

package Resposes;

import Requests.RequestNames;

public class BuyResponse implements Response {
   private static final RequestNames.RequestName COMMAND = RequestNames.RequestName.BUY;

   private String response;

   public BuyResponse(String response){
       this.response = response;
   }
    @Override
    public RequestNames.RequestName getCommand() {
        return null;
    }

    @Override
    public String getResponse() {
        return COMMAND + ", " + response;
    }
}

package Requests;

import Resposes.BorrowResponse;
import Resposes.Response;
import main.Models.Book;
import main.Models.LibraryEntry;
import main.Models.OwningLibrary;
import main.Models.Visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BorrowRequest implements Request {
    RequestNames.RequestName Command = RequestNames.RequestName.BORROW;


    //a proxy for the library that the Visitor is being added to
    private OwningLibrary proxyLibrary;

    private String visitorId = "";
    private String bookIds = "";


    public BorrowRequest(OwningLibrary library, ArrayList<String> parameters) {
        switch(parameters.size()) {
            default:
                break;
            case 2:
                this.bookIds = parameters.get(1);
                if(bookIds.startsWith("{")) bookIds = bookIds.substring(1);
                if(bookIds.endsWith("}")) bookIds = bookIds.substring(0, bookIds.length() - 1);
                this.visitorId = parameters.get(0);
                break;
        }
        proxyLibrary = library;
    }

    @Override
    public Response performRequest() {
        ArrayList<Book> books = new ArrayList<Book>();
        Visitor v = proxyLibrary.getRegister().get(Long.parseLong(visitorId));

        for (String s : bookIds.split(",")) {
            proxyLibrary.visitorCheckOut(v,Long.parseLong(s));
        }

        LocalDate dueDate = LocalDate.of(proxyLibrary.getDate().getYear(), proxyLibrary.getDate().getMonth(),
                proxyLibrary.getDate().getDay());
        dueDate = dueDate.plusWeeks(1);
        return new BorrowResponse(dueDate);
    }
}

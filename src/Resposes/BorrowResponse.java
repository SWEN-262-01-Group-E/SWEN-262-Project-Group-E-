package Resposes;

import Requests.RequestNames;
import main.Models.LibraryEntry;

import java.time.LocalDate;
import java.util.ArrayList;

public class BorrowResponse implements Response {
    private final RequestNames.RequestName RESPONSE_NAME = RequestNames.RequestName.BORROW;

    private LocalDate dueDate;

    public BorrowResponse(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public RequestNames.RequestName getCommand() {
        return RESPONSE_NAME;
    }

    @Override
    public String getResponse() {
        return null;
    }
}

package main.Models;

import java.io.Serializable;

/**
 * Book class with # of available copies and # of checked out copies
 */

public class LibraryEntry implements Serializable {

    private long ISBN;
    private int totalCopies;
    private int copiesCheckedOut;

    public LibraryEntry(long ISBN, int totalCopies) {
        this.ISBN = ISBN;
        this.totalCopies = totalCopies;
        this.copiesCheckedOut = 0;
    }

    public void buyMoreCopies(int amountBought) {
        totalCopies += amountBought;
    }

    public void checkoutBook() {
        this.copiesCheckedOut += 1;
    }

    public void checkinBook() {
        this.copiesCheckedOut -= 1;
    }

    public Boolean canBeCheckedOut() {
        return (this.totalCopies > this.copiesCheckedOut);
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getCopiesCheckedOut() {
        return copiesCheckedOut;
    }

    public long getISBN(){
        return ISBN;
    }

    @Override
    public String toString() {
        return "LibraryEntry{" +
                "ISBN=" + ISBN +
                ", totalCopies=" + totalCopies +
                ", copiesCheckedOut=" + copiesCheckedOut +
                '}';
    }
}

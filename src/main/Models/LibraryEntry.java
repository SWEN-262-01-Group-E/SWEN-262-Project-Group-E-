package main.Models;

/**
 * Book class with # of available copies and # of checked out copies
 */

public class LibraryEntry {

    private int ISBN;
    private int totalCopies;
    private int copiesCheckedOut;

    public LibraryEntry(int ISBN, int totalCopies) {
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

    public int getISBN(){
        return ISBN;
    }
}

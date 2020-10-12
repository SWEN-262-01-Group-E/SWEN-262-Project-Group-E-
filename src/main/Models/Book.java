package main.Models;

import java.util.Date;

/**
 * A model to represent a book in the Library Management System
 *
 * @author Joseph Saltalamacchia
 */
public class Book {

    private int ISBN;
    private String publisher;
    private Date publishDate;
    private int totalPages;

    /**
     * Creates a new Book object with a valid ISBN, publisher, date of publish, total number of pages, and total number of copies
     *
     * @param ISBN The unique identifying number for this book
     * @param publisher The publisher of this book
     * @param publishDate The date this book was published
     * @param totalPages  The total number of pages in a given copy of this book
     */
    //todo we should probably check that all of these values are valid
    public Book(int ISBN, String publisher, Date publishDate, int totalPages) {
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.totalPages = totalPages;
    }

    /**
     * returns the unique identification number for this book
     * @return the unique identification number for this book
     */
    public int getISBN() {
        return ISBN;
    }

    //There is on "set" method for the ISBN because it should never change

    /**
     * retruns this book's publisher
     * @return the name of this book's publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * sets this books publisher to the entered string
     * @param publisher the new name of the publisher for this book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Returns the date this book was published
     * @return the date this book was published
     */
    public Date getPublishDate() {
        return publishDate;
    }

    //there is no "set" method for the publish date because it should never change

    /**
     * The total number of pages in this book
     * @return the total number of pages in this book
     */
    public int getTotalPages() {
        return totalPages;
    }

    //there is no "set" method for the publish date because it should never change

}

package com.main.m_cogdell_libraryassignment4;

public class Books {
    private static Integer bookID;
    private static String title;
    private static String genre;
    private static Integer publicationYear;
    private static String ISBN;
    private static Integer availableCopies;

    public Books(Integer bookID,String title, String genre, Integer publicationYear,
                String ISBN, Integer availableCopies) {
        this.bookID = bookID;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.availableCopies = availableCopies;
    }

    public Books() {}

    public Integer getBookID() { return bookID; }
    public void setBookID(Integer bookID) { this.bookID = bookID; }
    public String getTitle() { return title; }
    public void setTitle(String title) {this.title = title;}
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Integer getPublicationYear(){ return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer availableCopies) { this.availableCopies = availableCopies; }

}

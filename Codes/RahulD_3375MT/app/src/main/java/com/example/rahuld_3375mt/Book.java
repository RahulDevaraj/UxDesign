package com.example.rahuld_3375mt;

public class Book {
    String bookName;
    int bookPic;
    boolean readStatus;
    boolean favoriteStatus;

    public Book(String bookName, int bookPic) {
        this.bookName = bookName;
        this.bookPic = bookPic;
        readStatus = false;
        favoriteStatus = false;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPic() {
        return bookPic;
    }

    public void setBookPic(int bookPic) {
        this.bookPic = bookPic;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public boolean isFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(boolean favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }
}

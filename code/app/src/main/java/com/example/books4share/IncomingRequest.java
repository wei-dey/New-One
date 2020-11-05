package com.example.books4share;

import java.io.Serializable;

public class IncomingRequest implements Serializable {
    private String bookName;
    private String borrowerName;
    private int index = -1;

    IncomingRequest(String bookName, String borrowerName) {
        this.bookName = bookName;
        this.borrowerName = borrowerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
package com.java.spring.bean;

import java.util.List;

/**
 * @author liushuo
 * @version 1.0
 */
public class BookStore {
    private List<String> bookList;

    public List<String> getBookList() {
        return bookList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "BookStore{" +
                "bookList=" + bookList +
                '}';
    }

    public BookStore() {

    }
}

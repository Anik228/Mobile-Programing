package com.example.andy.sqldemo;

public class BorrowList {
    String key = "";
    String borrower = "";
    String bookname = "";
    String borrowdate = "";
    String returndate = "";
    //    String budget = "";
//    String email = "";
//    String phone = "";
//    String description = "";
//    String eventType = "";
    public BorrowList (String key, String borrower, String bookname, String borrowdate,String returndate){
        this.key = key;
        this.borrower = borrower;
        this.bookname = bookname;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
//        this.budget = budget;
//        this.email = email;
//        this.phone = phone;
//        this.description = description;
//        this.eventType = eventType;
    }
}

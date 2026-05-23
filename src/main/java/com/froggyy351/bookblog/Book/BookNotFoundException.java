package com.froggyy351.bookblog.Book;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message){
        super(message); // 親のコンストラクタにメッセージを渡す
    }
}

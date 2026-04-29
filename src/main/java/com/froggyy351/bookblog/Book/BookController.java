package com.froggyy351.bookblog.Book;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    // コンストラクタを使うことでリポジトリを受け取る（Springが自動で渡してくれる）
    // 「コンストラクタ注入」と言うらしい
    // これにより、テストコードでRepositoryの偽物を注入しやすくなり、テストがしやすいというメリットがあるらしい
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book){
        // save()は、保存したオブジェクトが戻り値になる
        return bookRepository.save(book);
    }
}

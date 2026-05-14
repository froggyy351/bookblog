package com.froggyy351.bookblog.Book;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Thymeleaf用コントローラー
@Controller
@RequestMapping("/books")
public class BookViewController {

    private final BookRepository bookRepository;

    // コンストラクタを使うことでリポジトリを受け取る（Springが自動で渡してくれる）
    // 「コンストラクタ注入」と言うらしい
    // これにより、テストコードでRepositoryの偽物を注入しやすくなり、テストがしやすいというメリットがあるらしい
    public BookViewController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @GetMapping
    public String selectBookList(Model model){
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList); //第1引数はThymeleafに渡す変数名、第2引数はこのメソッドでの変数名
        return "bookList"; //Thymeleafのファイル名を拡張子取ってStringで返す。templateの下にフォルダ切ってたらそれも指定要
    }

//    @GetMapping("/{id}")
//    public Book selectBooks(@PathVariable long id){
//        return bookRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"該当のIDは存在していません。"));
//    }
//
//    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable long id, @RequestBody Book book){
//        Book exsistingBook = bookRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"該当のIDは存在していません。"));
//        exsistingBook.setAuthor(book.getAuthor());
//        exsistingBook.setTitle(book.getTitle());
//        exsistingBook.setImage(book.getImage());
//        exsistingBook.setIsbn(book.getIsbn());
//        exsistingBook.setMemo(book.getMemo());
//        exsistingBook.setStatus(book.getStatus());
//        exsistingBook.setUpdatedAt(book.getUpdatedAt());
//        return bookRepository.save(exsistingBook);
//    }
//

//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable long id){
//        Book exsistingBook = bookRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"該当のIDは存在していません。"));
//        bookRepository.deleteById(exsistingBook.getId());
//    }
}

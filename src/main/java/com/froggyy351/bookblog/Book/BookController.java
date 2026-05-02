package com.froggyy351.bookblog.Book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    // コンストラクタを使うことでリポジトリを受け取る（Springが自動で渡してくれる）
    // 「コンストラクタ注入」と言うらしい
    // これにより、テストコードでRepositoryの偽物を注入しやすくなり、テストがしやすいというメリットがあるらしい
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        // save()は、保存したオブジェクトが戻り値になる
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> selectBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book selectBooks(@PathVariable long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"該当のIDは存在していません。"));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book){
        Book exsistingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"該当のIDは存在していません。"));
        exsistingBook.setAuthor(book.getAuthor());
        exsistingBook.setTitle(book.getTitle());
        exsistingBook.setImage(book.getImage());
        exsistingBook.setIsbn(book.getIsbn());
        exsistingBook.setMemo(book.getMemo());
        exsistingBook.setStatus(book.getStatus());
        exsistingBook.setUpdatedAt(book.getUpdatedAt());
        return bookRepository.save(exsistingBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        Book exsistingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"該当のIDは存在していません。"));
        bookRepository.deleteById(exsistingBook.getId());
    }
}

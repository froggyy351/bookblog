package com.froggyy351.bookblog.Book;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    // コンストラクタを使うことでリポジトリを受け取る（Springが自動で渡してくれる）
    // 「コンストラクタ注入」と言うらしい
    // これにより、テストコードでRepositoryの偽物を注入しやすくなり、テストがしやすいというメリットがあるらしい
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //全件SELECT
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    //id指定のSELECT
    public Book findById(long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"該当のIDは存在していません。"));
    }

    //INSERT
    public Book create(Book book){
        return bookRepository.save(book);
    }

    //UPDATE
    public Book update(long id,Book book){
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

    //DELETE
    public void delete(long id){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "該当のIDは存在していません。"));
        bookRepository.deleteById(existingBook.getId());
    }
}

package com.froggyy351.bookblog.Book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    // コンストラクタを使うことでリポジトリを受け取る（Springが自動で渡してくれる）
    // 「コンストラクタ注入」
    // これにより、テストコードでRepositoryの偽物を注入しやすくなり、テストがしやすいというメリットがある
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("該当のIDは存在していません"));
    }

    public Book create(Book book){
        return bookRepository.save(book);
    }

    public Book update(long id,Book book){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("該当のIDは存在していません"));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setImage(book.getImage());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setMemo(book.getMemo());
        existingBook.setStatus(book.getStatus());
        existingBook.setUpdatedAt(book.getUpdatedAt());
        return bookRepository.save(existingBook);
    }

    public void delete(long id){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("該当のIDは存在していません"));
        bookRepository.deleteById(existingBook.getId());
    }
}

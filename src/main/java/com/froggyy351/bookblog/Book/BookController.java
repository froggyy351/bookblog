package com.froggyy351.bookblog.Book;

import com.froggyy351.bookblog.Book.dto.GoogleBooksResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// API用コントローラー（将来React連携用）

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@Valid @RequestBody Book book){
        // save()は、保存したオブジェクトが戻り値になる
        return bookService.create(book);
    }

    @GetMapping
    public List<Book> selectBooks(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book selectBooks(@PathVariable long id){
        return bookService.findById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id,@Valid @RequestBody Book book){
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.delete(id);
    }

    @GetMapping("/search")
    public GoogleBooksResponseDto searchBooks(@RequestParam String isbn){
        return bookService.searchByIsbn(isbn);
    }
}

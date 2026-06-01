package com.froggyy351.bookblog.Book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    GoogleBooksClient googleBooksClient;

    @InjectMocks
    BookService bookService;

    /**
     * 正常系：findById
     */
    @Test
    public void findById_withExistingId_returnsBook(){
        //Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitle("テスト本");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        //Act
        Book result = bookService.findById(1L);

        //Assert
        assertThat(result).isEqualTo(book);
    }

    /**
     * 異常系：findById
     */
    @Test
    public void findById_withNonExistentId_throwsBookNotFoundException(){
        //Arrange
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        //Act, Assert
        assertThatThrownBy(() -> bookService.findById(99L))
                .isInstanceOf(BookNotFoundException.class);
    }

    /**
     * 正常系：findAll
     */
    @Test
    public void findAll_withExistingBooks_returnsBookList(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("テスト本１");
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> results = bookService.findAll();

        assertThat(results).hasSize(1);
    }

    /**
     * 正常系：create
     */
    @Test
    public void create_withValidBook_returnsBook(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("テスト本");
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.create(book);

        assertThat(result).isEqualTo(book);
    }

    /**
     * 正常系：update
     */
    @Test
    public void update_withExistingId_returnsBook(){
        Book oldBook = new Book();
        oldBook.setId(1L);
        oldBook.setTitle("古い本");

        Book newBook = new Book();
        newBook.setTitle("新しい本");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(oldBook));
        when(bookRepository.save(oldBook)).thenReturn(newBook);

        Book result = bookService.update(1L, newBook);

        assertThat(result.getTitle()).isEqualTo("新しい本");
    }

    /**
     * 正常系：delete
     */
    @Test
    public void delete_withExistingId_callsDeleteById(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("削除される本");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.delete(1L);

        verify(bookRepository).deleteById(1L);
    }
}

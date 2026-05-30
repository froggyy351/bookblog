package com.froggyy351.bookblog.Book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
}

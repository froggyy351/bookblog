package com.froggyy351.bookblog.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockitoBean
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * 正常系：selectBooks（全件）
     */
    @Test
    public void selectBooks_ok_returns200() throws Exception{
        //Arrange
        Book book = new Book();
        book.setTitle("テスト本");
        when(bookService.findAll()).thenReturn(List.of(book));

        //Act+Assert
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("テスト本"));
    }

    /**
     * 正常系：selectBooks（１件）
     */
    @Test
    public void selectBooks_withId_returns200() throws Exception{
        //Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitle("テスト本");
        when(bookService.findById(1L)).thenReturn(book);

        //Act+Assert
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("テスト本"));
    }

    /**
     * 異常系：selectBooks（１件）
     */
    @Test
    public void selectBooks_withNoExistingId_returns404() throws Exception{
        //Arrange
        when(bookService.findById(99L)).thenThrow(BookNotFoundException.class);

        //Act+Assert
        mockMvc.perform(get("/api/books/99"))
                .andExpect(status().isNotFound());
    }

    /**
     * 正常系：updateBook
     */
    @Test
    public void updateBook_withId_returns200() throws Exception{
        //Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitle("テスト本");
        when(bookService.update(eq(1L), any(Book.class))).thenReturn(book);

        //Act+Assert
        mockMvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"テスト本update\", " +
                        "\"author\":\"テストさん\", " +
                        "\"isbn\":\"978-4-06-139801-6\", " +
                        "\"status\":\"1\"}"))
                .andExpect(status().isOk());
    }

    /**
     * 正常系：createBook
     */
    @Test
    public void createBook_withBook_returns200() throws Exception{
        //Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitle("テスト本");
        when(bookService.create(any(Book.class))).thenReturn(book);

        //Act+Assert
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"テスト本\", " +
                                "\"author\":\"テストさん\", " +
                                "\"isbn\":\"978-4-06-139801-6\", " +
                                "\"status\":\"1\"}"))
                .andExpect(status().isOk());
    }

    /**
     * 正常系：deleteBook
     */
    @Test
    public void deleteBook_withId_returns200() throws Exception{
        //Arrange
        doNothing().when(bookService).delete(1L);

        //Act+Assert
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
    }
}

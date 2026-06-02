package com.froggyy351.bookblog.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockitoBean
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * 正常系：selectBooks
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
}

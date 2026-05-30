package com.froggyy351.bookblog.Book;

import com.froggyy351.bookblog.Book.dto.GoogleBooksResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleBooksClient {

    @Value("${google.books.api-key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();
    public GoogleBooksResponseDto searchByIsbn(String isbn){
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + apiKey;
        return restClient.get().uri(url).retrieve().body(GoogleBooksResponseDto.class);
    }
}

package com.froggyy351.bookblog.Book;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {

    //IDはLong型にするのがベストプラクティスらしい
    //intは32bit、longは64bit。intでも21億らしいが以外と枯渇するらしいので、longの922京にしておくのが無難らしい
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成 Postgres側でCreateTableする時「serial」にする必要あり
    private long id;

    @Column(name = "title" ,nullable = false)
    @NotBlank
    @Size(max = 200)
    private String title;

    @Column(name = "author" ,nullable = false)
    @NotBlank
    @Size(max = 200)
    private String author;

    @Column(name = "isbn" ,nullable = false)
    @NotBlank
    @Pattern(regexp = "^(978|979)[0-9-]{14}$")  //ISBN-13のみ対応。2007年以前の書籍は対象外と判断した
    @Size(min = 13, max = 17)
    private String isbn;

    @Column(name = "image")
    @Size(max = 500)
    @URL
    private String image;

    @Column(name = "memo")
    @Size(max = 1000)
    private String memo;

    @Column(name = "status" ,nullable = false)
    @Min(1)
    @Max(3)
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

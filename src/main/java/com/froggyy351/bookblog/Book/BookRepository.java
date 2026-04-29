package com.froggyy351.bookblog.Book;

import org.springframework.data.jpa.repository.JpaRepository;

// Repositoryをextendesした中身空っぽのインターフェースを作るだけでOK
// <Book, Long>ここは、第1引数にEntityの型、第2引数に主キーの型を指定
// これでSpringがインターフェースからImplimentsしてくれてインスタンス化してくれる
// この記述だけでCRUDのメソッドが使えるようになるらしい！
// ※補足：ジェネリクスは基本型の例えば
// int、longは使えないため、参照型のInteger、Longとすること
public interface BookRepository extends JpaRepository<Book, Long> {
}

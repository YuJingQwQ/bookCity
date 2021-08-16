package com.yt.boot.service;

import com.yt.boot.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-10 21:37
 */
public interface BookService {
    public List<Book> getAllBooks();

    public Long getTotalNumber();

    List<Book> getAllBooksByPrice(Integer min,Integer max);

    Long getTotalNumberByPrice(Integer min, Integer max);

    Book selectBookById(Integer id);

    Integer update(Book book);

    Integer delete(Integer id);

    Integer add(Book book);
}

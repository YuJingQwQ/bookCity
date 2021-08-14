package com.yt.boot.service.impl;

import com.yt.boot.dao.BookMapper;
import com.yt.boot.pojo.Book;
import com.yt.boot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-10 21:38
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.queryAllBooks();
    }

    @Override
    public Long getTotalNumber() {
        return bookMapper.queryTotalBookNumber();
    }

    @Override
    public List<Book> getAllBooksByPrice(Integer min, Integer max) {
        return bookMapper.queryBooksByPrice(min,max);
    }

    @Override
    public Long getTotalNumberByPrice(Integer min, Integer max) {
        return bookMapper.queryTotalBookNumberByPrice(min,max);
    }

    @Override
    public Book commonBook(Book book) {
        return bookMapper.commonBook(book);
    }

    @Transactional
    @Override
    public Integer update(Book book) {
        return bookMapper.update(book);
    }

    @Transactional
    @Override
    public Integer delete(Book book) {
        return bookMapper.delete(book);
    }

    @Transactional
    @Override
    public Integer add(Book book) {
        return bookMapper.add(book);
    }
}

package com.hyt.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyt.book.mapper.BookMapper;
import com.hyt.book.pojo.Book;
import com.hyt.book.service.BookService;
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
        return bookMapper.selectList(null);
    }

    @Override
    public Long getTotalNumber() {
        return bookMapper.selectCount(null).longValue();
    }

    @Override
    public List<Book> getAllBooksByPrice(Integer min, Integer max) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.ge("price", min).le("price", max);
        return bookMapper.selectList(wrapper);
    }

    @Override
    public Long getTotalNumberByPrice(Integer min, Integer max) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.ge("price", min).le("price", max);
        return bookMapper.selectCount(wrapper).longValue();
    }

    @Override
    public Book selectBookById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Transactional
    @Override
    public Integer update(Book book) {
        QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
        wrapper.eq("id", book.getId());
        return bookMapper.update(book, wrapper);
    }

    @Transactional
    @Override
    public Integer delete(Integer id) {
        return bookMapper.delete(new QueryWrapper<Book>().eq("id", id));
    }

    @Transactional
    @Override
    public Integer add(Book book) {
        return bookMapper.insert(book);
    }
}

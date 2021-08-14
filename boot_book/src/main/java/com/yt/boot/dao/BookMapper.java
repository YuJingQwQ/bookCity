package com.yt.boot.dao;

import com.yt.boot.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-10 21:38
 */
@Mapper
public interface BookMapper {

    List<Book> queryAllBooks();

    Long queryTotalBookNumber();

    List<Book> queryBooksByPrice(@Param(value = "min") Integer min,@Param(value = "max") Integer max);

    Long queryTotalBookNumberByPrice(@Param("min") Integer min, @Param("max") Integer max);

    Book commonBook(Book book);

    Integer update(Book book);

    Integer delete(Book book);

    Integer add(Book book);
}

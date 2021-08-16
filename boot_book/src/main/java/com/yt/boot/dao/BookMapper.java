package com.yt.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.boot.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-10 21:38
 */
public interface BookMapper extends BaseMapper<Book> {

}

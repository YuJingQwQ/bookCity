package com.hyt.book.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.book.pojo.Book;
import com.hyt.book.pojo.Cart;
import com.hyt.book.pojo.User;
import com.hyt.book.service.BookService;
import com.hyt.book.service.CartService;
import com.hyt.book.utils.MyWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-10 21:37
 */
@Controller
public class IndexController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CartService cartService;

    @GetMapping(value = {"/"})
    public String toIndexPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, HttpSession session) {
        Integer pageSize = 4;
        Integer totalNumber = ((Number) bookService.getTotalNumber()).intValue();
        pn = MyWebUtils.validPageNumber(pn, totalNumber, pageSize);
        PageHelper.startPage(pn, pageSize);
        List<Book> books = bookService.getAllBooks();
        PageInfo<Book> pageInfo = new PageInfo<>(books, 5);
        model.addAttribute("page", pageInfo);
        model.addAttribute("pageUrl", "?pn=");
        model.addAttribute("totalItemNumber", totalNumber);

        Object user = session.getAttribute("user");
        if (user != null) {
            user = (User) user;
            Cart cart = cartService.getCart(((User) user).getId());
            session.setAttribute("cart", cart);
        }
        //thymeleaf自带前缀: /templates  后缀: .html
        return "index";
    }

    @GetMapping(value = {"/"}, params = {"min", "max"})
    public String toIndexPageByPrice(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                     @RequestParam(required = false) Integer min,
                                     @RequestParam(required = false) Integer max,
                                     Model model) {
        if (min == null && max == null) {
            return "redirect:/";
        }
        if (min == null || min < 0)
            min = 0;
        if (max == null || max > Integer.MAX_VALUE)
            max = Integer.MAX_VALUE;
        if (min > max) {
            min = 0;
            max = Integer.MAX_VALUE;
        }

        Integer pageSize = 4;
        Integer totalNumber = ((Number) bookService.getTotalNumberByPrice(min, max)).intValue();
        pn = MyWebUtils.validPageNumber(pn, totalNumber, pageSize);
        PageHelper.startPage(pn, pageSize);
        List<Book> books = bookService.getAllBooksByPrice(min, max);
        PageInfo<Book> pageInfo = new PageInfo<>(books, 5);
        model.addAttribute("page", pageInfo);
        model.addAttribute("totalItemNumber", totalNumber);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("pageUrl", "/?min=" + min + "&max=" + max + "&pn=");
        //thymeleaf自带前缀: /templates  后缀: .html
        return "index";
    }
}

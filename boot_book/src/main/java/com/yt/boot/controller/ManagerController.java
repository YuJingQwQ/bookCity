package com.yt.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yt.boot.pojo.Book;
import com.yt.boot.pojo.Order;
import com.yt.boot.pojo.OrderItem;
import com.yt.boot.service.BookService;
import com.yt.boot.service.OrderService;
import com.yt.boot.utils.MyWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 14:53
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/toManagerPage")
    public String toManagerPage() {

        return "pages/manager/manager";
    }

    @GetMapping("/toBookManagerPage")
    public String toBookManagerPage(@RequestParam(defaultValue = "1") Integer pn, Model model) {
        Integer pageSize = 5;
        Integer totalNumber = ((Number) bookService.getTotalNumber()).intValue();
        pn = MyWebUtils.validPageNumber(pn, totalNumber, pageSize);
        PageHelper.startPage(pn, pageSize);
        List<Book> books = bookService.getAllBooks();
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        model.addAttribute("page", pageInfo);
        model.addAttribute("pageUrl", "/manager/toBookManagerPage?pn=");
        return "pages/manager/book_manager";
    }

    @GetMapping("/toBookEditPage")
    public String toBookEditPage(Integer bookId, Integer pn, Model model) {
        Book book = bookService.selectBookById(bookId);
        model.addAttribute("book", book);
        model.addAttribute("pn", pn);
        return "pages/manager/book_edit";
    }

    @GetMapping("/toBookAddPage")
    public String toBookAddPage(Integer pn, Model model) {
        model.addAttribute("pn", pn);
        return "pages/manager/book_edit";
    }

    @GetMapping("/toOrderPage")
    public String toOrderPage(@RequestParam(defaultValue = "1") Integer pn, Model model) {
        Integer pageSize = 5;
        Integer totalOrderNumber = orderService.getTotalOrderNumber();
        List<Order> orders = orderService.getOrders(pn, pageSize, totalOrderNumber);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        model.addAttribute("page", pageInfo);
        model.addAttribute("pageUrl", "/order/toOrderPage?pn=");
        model.addAttribute("totalItemNumber", totalOrderNumber);
        return "pages/order/order";
    }

    @GetMapping("/toOrderManagerPage")
    public String toOrderManagerPage(String orderId, Model model) {
        List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
        Order order = orderService.getOrder(orderId);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", order);
        return "/pages/order/order_manager";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Integer id, Integer pn, Book book) {
        System.out.println(book);
        bookService.update(book);
        return "redirect:/manager/toBookManagerPage?pn=" + pn;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id, Integer pn) {
        bookService.delete(id);
        return "redirect:/manager/toBookManagerPage?pn=" + pn;
    }

    @PostMapping("/add")
    public String add(Integer pn, Book book) {
        bookService.add(book);
        return "redirect:/manager/toBookManagerPage?pn=" + pn;
    }
}

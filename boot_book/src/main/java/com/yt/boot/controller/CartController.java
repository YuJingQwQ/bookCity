package com.yt.boot.controller;

import com.github.pagehelper.PageInfo;
import com.yt.boot.pojo.Book;
import com.yt.boot.pojo.Cart;
import com.yt.boot.pojo.CartItem;
import com.yt.boot.pojo.User;
import com.yt.boot.service.BookService;
import com.yt.boot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-11 22:48
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @GetMapping("/toCartPage")
    public String toCartPage(@RequestParam(defaultValue = "1") Integer pn, HttpSession session, Model model) {
        Integer pageSize = 5;
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        Cart cart = cartService.getCart(id);
        Integer totalItemNumber = cartService.getTotalItemNumber(id);
        List<CartItem> cartItems = cartService.getCartItems(id, pn, pageSize,totalItemNumber);
        cart.setCartItems(cartItems);
        PageInfo<CartItem> pageInfo = new PageInfo<>(cartItems);
        model.addAttribute("page", pageInfo);
        model.addAttribute("pageUrl", "/cart/toCartPage?pn=");
        model.addAttribute("totalItemNumber",totalItemNumber);
        session.setAttribute("cart", cart);
        return "pages/cart/cart";
    }

    @ResponseBody
    @PostMapping("/add/{id}")
    public Cart add(@PathVariable(name = "id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Book book = bookService.selectBookById(id);
        cartService.add(userId, book);
        return cartService.getCart(userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id, @RequestParam(defaultValue = "1") Integer pn, HttpSession session) {
        User user = (User) session.getAttribute("user");

        //获取数据库中对应的CartItem
        CartItem cartItem = new CartItem();
        cartItem.setUserId(user.getId());
        cartItem.setItemId(id);
        CartItem queriedCartItem = cartService.getCartItemByUserIdAndItemId(cartItem);

        //获取数据库中对应的Cart
        Cart queriedCart = cartService.getCart(user.getId());

        //修改数据库中对应的Cart
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setTotalItemNumber(queriedCart.getTotalItemNumber() - queriedCartItem.getNumber());
        cart.setTotalPrice(queriedCart.getTotalPrice().subtract(queriedCartItem.getTotalPrice()));
        cartService.updateCart(cart);

        //删除数据库中对应的CartItem
        cartService.deleteCartItem(cartItem);

        return "redirect:/cart/toCartPage?pn=" + pn;
    }

    @DeleteMapping("/clear")
    public String clearCartItems(HttpSession session) {
        User user = (User) session.getAttribute("user");

        //修改数据库中对应的Cart
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setLastItemName("");
        cart.setTotalItemNumber(0);
        cart.setTotalPrice(new BigDecimal(0));
        cartService.updateCart(cart);

        //删除数据库中对应的CartItem
        cartService.clearCartItems(user.getId());

        return "redirect:/cart/toCartPage";
    }
}

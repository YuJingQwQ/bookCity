package com.yt.boot.controller;

import com.github.pagehelper.PageInfo;
import com.yt.boot.pojo.Cart;
import com.yt.boot.pojo.CartItem;
import com.yt.boot.pojo.Order;
import com.yt.boot.pojo.User;
import com.yt.boot.service.CartService;
import com.yt.boot.service.OrderService;
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
 * @create 2021-08-13 22:20
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    public String checkout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();

        Cart cart = cartService.getCart(userId);

        if (cart.getTotalItemNumber() == 0) {
            return "redirect:/cart/toCartPage";
        }

        String orderId = orderService.createOrder(userId);

        List<CartItem> cartItems = cartService.getCartItemsByUserId(userId);

        orderService.createOrderItems(orderId, cartItems);

        cartService.clearCartItems(userId);

        cartService.clearCart(cart.getUserId());

        return "redirect:/cart/toCartPage";
    }

    @ResponseBody
    @PutMapping("/sendGoods")
    public String sendGoods(String orderId){
        //订单状态:1表示已发货
        Integer status = 1;
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(status);
        orderService.updateStatus(order);
        return "";
    }

}

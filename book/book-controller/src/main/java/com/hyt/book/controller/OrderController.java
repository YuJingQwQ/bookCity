package com.hyt.book.controller;

import com.hyt.book.pojo.Cart;
import com.hyt.book.pojo.CartItem;
import com.hyt.book.pojo.User;
import com.hyt.book.service.CartService;
import com.hyt.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    public String sendGoods(String orderId,Integer itemId){
        //订单状态:1表示已发货
        Integer status = 1;
        orderService.updateOrderItemStatus(orderId,itemId);

//        Order order = new Order();
//        order.setOrderId(orderId);
//        order.setStatus(status);
//        orderService.updateStatus(order);
        return "";
    }

}

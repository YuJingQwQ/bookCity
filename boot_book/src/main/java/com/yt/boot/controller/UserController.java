package com.yt.boot.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yt.boot.pojo.Cart;
import com.yt.boot.pojo.User;
import com.yt.boot.service.CartService;
import com.yt.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-11 15:01
 */
@Controller()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Producer kaptcha;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/toLoginPage")
    public String toLoginPage(Model model, HttpSession session) {
        Object username = session.getAttribute("username");
        Object errorMsg = session.getAttribute("errorMsg");
        if (username != null) {
            session.removeAttribute("username");
            model.addAttribute("username", username);
        }
        if (errorMsg != null) {
            session.removeAttribute("errorMsg");
            model.addAttribute("errorMsg", errorMsg);
        }
        return "pages/user/login";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxToLoginPage")
    public Map<String,String> ajaxToLoginPage(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        map.put("method","redirect");
        String path = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/user/toLoginPage";
        map.put("path",path);
        return map;
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @RequestMapping("/toRegisterPage")
    public String toRegisterPage(HttpSession session, Model model) {
        Object errorMsg = session.getAttribute("errorMsg");
        Object username = session.getAttribute("username");
        Object email = session.getAttribute("email");
        if (errorMsg != null) {
            session.removeAttribute("errorMsg");
            model.addAttribute("errorMsg", errorMsg);
        }
        if (username != null) {
            session.removeAttribute("username");
            model.addAttribute("username", username);
        }
        if (email != null) {
            session.removeAttribute("email");
            model.addAttribute("email", email);
        }
        return "pages/user/register";
    }

    @RequestMapping("/toLoginSuccessPage")
    public String toLoginSuccessPage() {
        return "pages/user/login_success";
    }

    @RequestMapping("/toRegisterSuccessPage")
    public String toRegisterSuccessPage() {
        return "pages/user/register_success";
    }

    /**
     * 获取验证码图片
     *
     * @param session
     * @param response
     */
    @GetMapping("/kaptcha.jpg")
    public void getkaptcha(HttpSession session, HttpServletResponse response) {
        String code = kaptcha.createText();
        session.setAttribute("kaptchaCode", code);
        ServletOutputStream out = null;
        try {
            BufferedImage bi = kaptcha.createImage(code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 注册用户,并将用户以"user"为key保存到session域中
     *
     * @param code
     * @param user
     * @param result
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/register")
    public String register(String code, @Valid User user, BindingResult result, Model model, HttpSession session) {
        Object kaptchaCode = session.getAttribute("kaptchaCode");
        session.removeAttribute("kaptchaCode");
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (kaptchaCode == null) {
            session.setAttribute("errorMsg", "验证码为空!");
            return "redirect:/user/toRegisterPage";
        } else if (!kaptchaCode.equals(code)) {
            session.setAttribute("errorMsg", "验证码错误!");
            return "redirect:/user/toRegisterPage";
        }

        if (result.hasFieldErrors()) {
            //获得所有的属性错误
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                //把错误信息存放在model对象中
                session.setAttribute("errorMsg", fieldError.getDefaultMessage());
                return "redirect:/user/toRegisterPage";
            }
        }
//        User tempUser1 = new User();
//        tempUser1.setUsername(user.getUsername());
        User isExisted = userService.getUserByUsername(user.getUsername());
        if (isExisted != null) {
            session.setAttribute("errorMsg", "用户已存在!");
            return "redirect:/user/toRegisterPage";
        }
        userService.addUser(user);
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setTotalPrice(new BigDecimal(0));
        cart.setTotalItemNumber(0);
        cart.setLastItemName("");
        cartService.createCart(cart);
        session.setAttribute("user", user);
        return "redirect:/user/toRegisterSuccessPage";
    }

    /**
     * 登录程序
     *
     * @param user
     * @param result
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@Valid User user, BindingResult result,
                        Model model, HttpSession session) {
        session.setAttribute("username", user.getUsername());
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                if ("username".equals(field) || "password".equals(field)) {
                    session.setAttribute("errorMsg", fieldError.getDefaultMessage());
                    return "redirect:/user/toLoginPage";
                }
            }
        }

        User tempUser = userService.getUserByUsername(user.getUsername());
        if (tempUser != null) {
            Cart cart = cartService.getCart(tempUser.getId());
            session.setAttribute("user", tempUser);
            session.setAttribute("cart", cart);
            return "redirect:/user/toLoginSuccessPage";
        }
        session.setAttribute("errorMsg", "账号或密码错误!");
        return "redirect:/user/toLoginPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

}

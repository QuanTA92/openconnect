package com.fpt.openconnect.controller;

import com.fpt.openconnect.service.CheckOutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckOutController {

    @Autowired
    private CheckOutService checkOutService;

    @GetMapping("/{userId}")
    public String createOrder(@PathVariable int userId, @RequestParam String returnUrl, Model model) {
        try {
            String paymentUrl = checkOutService.createOrder(userId, returnUrl);
            return "redirect:" + paymentUrl;
        } catch (RuntimeException ex) {
            model.addAttribute("error", "Lỗi khi tạo đường dẫn thanh toán: " + ex.getMessage());
            return "error-page"; // Trả về trang error-page để hiển thị thông báo lỗi
        }
    }

    @GetMapping("/vnpay-payment")
    public String orderReturn(HttpServletRequest request) {
        return checkOutService.orderReturn(request);
    }
}


package com.fpt.openconnect.service;

import com.fpt.openconnect.config.VNPayConfig;
import com.fpt.openconnect.entity.*;
import com.fpt.openconnect.entity.keys.ProductTicketOrderKeys;
import com.fpt.openconnect.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CheckOutService {

    private final CartService cartService;
    private final ProductTicketOrderRepository productTicketOrderRepository;
    private final OrdersRepository ordersRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;


    public String createOrder(int userId, String returnUrl) {
        try {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<CartEntity> carts = cartService.getCartsByUserId(userId);
            double totalPrice = carts.stream().mapToDouble(CartEntity::getPriceCart).sum();

            OrdersEntity order = new OrdersEntity();
            order.setUser(user);
            order.setPrice(totalPrice);
            order = ordersRepository.save(order);

            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
            String vnp_IpAddr = "127.0.0.1";
            String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
            String orderType = "order-type";

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf((long) (totalPrice * 100))); // Convert to long before converting to String
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", user.getUsername());
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", returnUrl + VNPayConfig.vnp_Returnurl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            // Build hash data
            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            for (String fieldName : fieldNames) {
                String fieldValue = vnp_Params.get(fieldName);
                if (fieldValue != null && !fieldValue.isEmpty()) {
                    try {
                        hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString())).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        if (fieldNames.indexOf(fieldName) < fieldNames.size() - 1) {
                            hashData.append('&');
                            query.append('&');
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Append secure hash to query string
            String queryUrl = query.toString();
            String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + URLEncoder.encode(vnp_SecureHash, StandardCharsets.US_ASCII.toString());

            // Construct final payment URL
            return VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Lỗi khi tạo đường dẫn thanh toán: " + ex.getMessage());
        }
    }

    @Transactional
    public String orderReturn(HttpServletRequest request) {
        try {
            // Lấy userId từ request parameter hoặc từ cookie
            String userIdParam = request.getParameter("userId");
            if (StringUtils.isEmpty(userIdParam)) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("userId".equals(cookie.getName())) {
                            userIdParam = cookie.getValue();
                            break;
                        }
                    }
                }
            }

            // Kiểm tra userId có rỗng không
            if (StringUtils.isEmpty(userIdParam)) {
                throw new IllegalArgumentException("userId is null");
            }

            // Chuyển đổi userId sang kiểu dữ liệu phù hợp
            int userId = Integer.parseInt(userIdParam);

            // Kiểm tra xem đã có đơn hàng được tạo với userId này chưa
            Optional<OrdersEntity> existingOrder = ordersRepository.findFirstByUserIdOrderByCreateDateDesc(userId);
            OrdersEntity order;
            if (existingOrder.isPresent()) {
                order = existingOrder.get();
            } else {
                // Lấy đối tượng UserEntity từ userId
                UserEntity user = userRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

                // Tạo một đơn hàng mới
                order = new OrdersEntity();
                order.setUser(user);
                order.setCreateDate(new Date());// Thiết lập ngày tạo đơn hàng

                // Lưu đơn hàng mới vào cơ sở dữ liệu để lấy orderId
                order = ordersRepository.save(order);
            }

            // Lấy danh sách sản phẩm trong giỏ của userId
            List<CartEntity> carts = cartRepository.findByUserEntityId(userId);
            for (CartEntity cart : carts) {
                ProductTicketOrderEntity productTicketOrder = new ProductTicketOrderEntity();

                // Khởi tạo ProductTicketOrderKeys và gán vào productTicketOrder
                ProductTicketOrderKeys keys = new ProductTicketOrderKeys();
                productTicketOrder.setKeys(keys);

                // Tạo khóa cho productTicketOrder từ cart và order
                Integer productId = Optional.ofNullable(cart.getProductEntity())
                        .map(ProductEntity::getId)
                        .orElse(null);
                Integer ticketId = Optional.ofNullable(cart.getTicketEntity())
                        .map(TicketEntity::getId)
                        .orElse(null);

                // Thiết lập giá trị cho keys
                keys.setIdOrder(order.getId());
                keys.setIdProduct(productId);
                keys.setIdTicket(ticketId);

                if (productId == null) {
                    // Nếu idProduct của cart là null, thiết lập quantityProduct là null
                    productTicketOrder.setQuantityProduct(null);
                } else {
                    // Nếu có productId từ cart, tiếp tục xử lý bình thường
                    ProductEntity product = cart.getProductEntity();
                    productTicketOrder.setQuantityProduct(cart.getQuantityProduct());
                    productTicketOrder.setProductEntity(product);
                }

                productTicketOrder.setOrdersEntity(order);

                // Thiết lập các thuộc tính khác của productTicketOrder
                productTicketOrder.setQuantityTicket(cart.getQuantityTicket());
                productTicketOrder.setPrice(cart.getPriceCart());
                productTicketOrder.setCreateDate(new Date()); // Thiết lập ngày tạo productTicketOrder

                // Lưu productTicketOrder vào cơ sở dữ liệu
                productTicketOrderRepository.save(productTicketOrder);
            }

            // Nếu thanh toán thành công, thiết lập idStatus là 1
            boolean paymentSuccess = true; // Ví dụ: bạn cần cập nhật với trạng thái thanh toán từ request
            if (paymentSuccess) {
                order.setStatus(statusRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("Status not found with id: 1")));
            } else {
                // Nếu không thanh toán thành công, thiết lập idStatus là 2
                order.setStatus(statusRepository.findById(2).orElseThrow(() -> new EntityNotFoundException("Status not found with id: 2")));
            }

            // Lưu lại order sau khi thiết lập idStatus
            order = ordersRepository.save(order);

            // Xóa các sản phẩm trong giỏ sau khi đơn hàng được xử lý
            cartRepository.deleteAll(carts);

            // Chuyển hướng đến trang thành công
            return "redirect:/ordersuccess?orderId=" + order.getId() +
                    "&totalPrice=" + order.getPrice() +
                    "&paymentTime=" + order.getCreateDate();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error processing order: " + ex.getMessage());
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error processing order: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error processing order: " + ex.getMessage());
        }
    }







}



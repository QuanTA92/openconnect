package com.fpt.openconnect.controller;

import com.fpt.openconnect.entity.UserEntity;
import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.repository.UserRepository;
import com.fpt.openconnect.service.UserService;
import com.fpt.openconnect.service.imp.UserServiceImp;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> insertUser(@RequestParam String username,
                                                   @RequestParam String password,
                                                   @RequestParam String email,
                                                   @RequestParam String phone,
                                                   HttpServletResponse response) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            // Kiểm tra sự tồn tại của username
            Optional<UserEntity> existingUserByUsername = userRepository.findByUsername(username);
            if (existingUserByUsername.isPresent()) {
                baseResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                baseResponse.setError("Username already exists.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
            }

            // Kiểm tra sự tồn tại của email
            Optional<UserEntity> existingUserByEmail = userRepository.findByEmail(email);
            if (existingUserByEmail.isPresent()) {
                baseResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                baseResponse.setError("Email already exists.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
            }

            // Kiểm tra sự tồn tại của phone
            Optional<UserEntity> existingUserByPhone = userRepository.findByPhone(Integer.parseInt(phone));
            if (existingUserByPhone.isPresent()) {
                baseResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                baseResponse.setError("Phone number already exists.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
            }

            boolean isUserCreated = userServiceImp.insertUser(username, password, email, phone);
            if (isUserCreated) {
                baseResponse.setStatusCode(HttpStatus.OK.value());
                baseResponse.setMessage("User created successfully.");

                // Lấy thông tin người dùng sau khi đăng ký thành công
                Optional<UserEntity> newUser = userRepository.findByUsername(username);
                if (newUser.isPresent()) {
                    int userId = newUser.get().getId();
                    // Tạo cookie cho ID của người dùng
                    Cookie userIdCookie = new Cookie("userId", String.valueOf(userId));
                    userIdCookie.setMaxAge(24 * 60 * 60); // Thời gian sống của cookie: 1 ngày
                    userIdCookie.setPath("/"); // Đặt path của cookie
                    response.addCookie(userIdCookie);
                }

                // Tạo cookie cho người dùng sau khi đăng ký thành công
                Cookie cookie = new Cookie("authenticated", "true");
                cookie.setMaxAge(24 * 60 * 60); // Thời gian sống của cookie: 1 ngày
                cookie.setPath("/"); // Đặt path của cookie
                response.addCookie(cookie);

                // Lưu thông tin người dùng vào cookie (ví dụ: username)
                Cookie userCookie = new Cookie("username", username);
                userCookie.setMaxAge(24 * 60 * 60); // Thời gian sống của cookie: 1 ngày
                userCookie.setPath("/"); // Đặt path của cookie
                response.addCookie(userCookie);

                return ResponseEntity.ok(baseResponse);
            } else {
                baseResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                baseResponse.setError("User creation failed.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
            }
        } catch (IOException e) {
            baseResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            baseResponse.setError("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<BaseResponse> loginUser(@RequestParam String usernameOrEmailOrPhone, @RequestParam String password, HttpServletResponse response) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            boolean isAuthenticated = userServiceImp.loginUser(usernameOrEmailOrPhone, password);
            if (isAuthenticated) {
                // Lấy thông tin người dùng từ cơ sở dữ liệu
                Optional<UserEntity> user = userRepository.findByUsername(usernameOrEmailOrPhone);
                if (user.isPresent()) {
                    int userId = user.get().getId();
                    // Tạo cookie sau khi đăng nhập thành công
                    Cookie userIdCookie = new Cookie("userId", String.valueOf(userId));
                    userIdCookie.setMaxAge(24 * 60 * 60); // Thời gian sống của cookie: 1 ngày
                    userIdCookie.setPath("/"); // Đặt path của cookie
                    response.addCookie(userIdCookie);

                    // Tạo cookie cho việc xác thực đăng nhập
                    Cookie authenticatedCookie = new Cookie("authenticated", "true");
                    authenticatedCookie.setMaxAge(24 * 60 * 60); // Thời gian sống của cookie: 1 ngày
                    authenticatedCookie.setPath("/"); // Đặt path của cookie
                    response.addCookie(authenticatedCookie);

                    // Lưu thông tin người dùng vào cookie (ví dụ: username)
                    Cookie userCookie = new Cookie("username", usernameOrEmailOrPhone);
                    userCookie.setMaxAge(24 * 60 * 60); // Thời gian sống của cookie: 1 ngày
                    userCookie.setPath("/"); // Đặt path của cookie
                    response.addCookie(userCookie);

                    baseResponse.setStatusCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Login successful.");
                    baseResponse.setData(Collections.singletonMap("userId", userId)); // Trả về userId trong dữ liệu
                    return ResponseEntity.ok(baseResponse);
                } else {
                    baseResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                    baseResponse.setError("Invalid username/email/phone or password.");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(baseResponse);
                }
            } else {
                baseResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                baseResponse.setError("Invalid username/email/phone or password.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(baseResponse);
            }
        } catch (IOException e) {
            baseResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            baseResponse.setError("Error logging in: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
        }
    }



    @PutMapping("/{userName}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable String userName,
                                                   @RequestParam(required = false) String password,
                                                   @RequestParam(required = false) String email,
                                                   @RequestParam(required = false) String phone,
                                                   @RequestParam(required = false) String address,
                                                   @RequestParam(required = false) MultipartFile imageUser) {
        BaseResponse response = new BaseResponse();
        try {
            boolean isUpdated = userService.updateUser(userName, password, email, phone, address, imageUser);
            if (isUpdated) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("User updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("User not found with username: " + userName);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (IOException e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to update user due to an internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<BaseResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        BaseResponse baseResponse = new BaseResponse();

        // Xóa cookie "authenticated", "username", và "userId"
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authenticated") || cookie.getName().equals("username") || cookie.getName().equals("userId")) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }

        baseResponse.setStatusCode(HttpStatus.OK.value());
        baseResponse.setMessage("Logged out successfully.");
        return ResponseEntity.ok(baseResponse);
    }



}



package com.fpt.openconnect.service.imp;

import com.fpt.openconnect.payload.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserServiceImp {

    boolean insertUser(String userName, String password, String email, String phone) throws IOException;

    boolean loginUser(String usernameOrEmailOrPhone, String password) throws IOException;

    boolean updateUser(String userName, String password, String email, String phone, String address, MultipartFile imageUser) throws IOException;

    List<UserResponse> getProfileUserById(int idUser);
}

package com.fpt.openconnect.service;

import com.fpt.openconnect.entity.RoleEntity;
import com.fpt.openconnect.entity.UserEntity;
import com.fpt.openconnect.repository.RoleRepository;
import com.fpt.openconnect.repository.UserRepository;
import com.fpt.openconnect.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {

    @Value("${root.folder}")
    private String rootFolder;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean loginUser(String usernameOrEmailOrPhone, String password) throws IOException {
        // Kiểm tra xem usernameOrEmailOrPhone có phải là username, email hoặc phone
        Optional<UserEntity> user = userRepository.findByUsername(usernameOrEmailOrPhone);
        if (!user.isPresent()) {
            user = userRepository.findByEmail(usernameOrEmailOrPhone);
            if (!user.isPresent()) {
                // Kiểm tra theo phone chỉ nếu đối tượng phone được truyền vào có thể được chuyển đổi thành số nguyên
                try {
                    int phone = Integer.parseInt(usernameOrEmailOrPhone);
                    user = userRepository.findByPhone(phone);
                } catch (NumberFormatException e) {
                    // Nếu không phải là số nguyên, thì user vẫn sẽ không tồn tại
                }
            }
        }

        if (user.isPresent()) {
            // Kiểm tra mật khẩu
            if (user.get().getPassword().equals(password)) {
                // Đăng nhập thành công
                return true;
            }
        }
        // Đăng nhập không thành công
        return false;
    }



    @Override
    public boolean insertUser(String username, String password, String email, String phone) throws IOException {
        try {

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setEmail(email);
            userEntity.setPhone(Integer.parseInt(phone));

            // Tìm và gán role có id là 1
            RoleEntity role = roleRepository.findById(1).orElseThrow(() -> new RuntimeException("Role not found"));
            userEntity.setRole(role);

            userRepository.save(userEntity);
            return true;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUser(String userName, String password, String email, String phone, String address, MultipartFile imageUser) throws IOException {
        // Kiểm tra xem người dùng có tồn tại không
        Optional<UserEntity> existingUser = userRepository.findByUsername(userName);
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();
            // Cập nhật thông tin người dùng
            if (password != null) {
                user.setPassword(password);
            }
            if (email != null) {
                user.setEmail(email);
            }
            if (phone != null) {
                user.setPhone(Integer.parseInt(phone));
            }
            if (address != null) {
                user.setAddress(address);
            }
            // Xử lý hình ảnh người dùng nếu được cung cấp
            if (imageUser != null) {
                String imagePath = rootFolder + "\\" + imageUser.getOriginalFilename();
                Path path = Paths.get(rootFolder);
                Path imagePathCopy = Paths.get(imagePath);

                // Kiểm tra và tạo thư mục nếu không tồn tại
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }

                // Sao chép hình ảnh vào thư mục
                Files.copy(imageUser.getInputStream(), imagePathCopy, StandardCopyOption.REPLACE_EXISTING);

                // Cập nhật đường dẫn hình ảnh cho người dùng
                user.setImage(imagePath);
            }

            // Lưu thay đổi vào cơ sở dữ liệu
            userRepository.save(user);

            // Trả về true để chỉ ra rằng người dùng đã được cập nhật thành công
            return true;
        } else {
            // Trả về false nếu không tìm thấy người dùng
            return false;
        }
    }





}

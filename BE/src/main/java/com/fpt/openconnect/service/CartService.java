package com.fpt.openconnect.service;
import com.fpt.openconnect.entity.CartEntity;
import com.fpt.openconnect.entity.ProductEntity;
import com.fpt.openconnect.entity.TicketEntity;
import com.fpt.openconnect.entity.UserEntity;
import com.fpt.openconnect.payload.response.CartResponse;
import com.fpt.openconnect.repository.CartRepository;
import com.fpt.openconnect.repository.ProductRepository;
import com.fpt.openconnect.repository.TicketRepository;
import com.fpt.openconnect.repository.UserRepository;
import com.fpt.openconnect.service.imp.CartServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public boolean insertCart(int quantityProduct, int quantityTicket, int idUser, int idProduct, int idTicket) {
        try {
            // Tìm giỏ hàng của người dùng
            List<CartEntity> cartEntities = cartRepository.findByUserEntityId(idUser);

            CartEntity cartToUpdate = null;

            // Kiểm tra xem giỏ hàng đã tồn tại hay chưa
            for (CartEntity cartEntity : cartEntities) {
                // Nếu giỏ hàng đã tồn tại trong cơ sở dữ liệu
                if ((idProduct != 0 && cartEntity.getProductEntity() != null && cartEntity.getProductEntity().getId() == idProduct)
                        || (idTicket != 0 && cartEntity.getTicketEntity() != null && cartEntity.getTicketEntity().getId() == idTicket)) {
                    cartToUpdate = cartEntity;
                    break;
                }
            }

            // Nếu giỏ hàng đã tồn tại
            if (cartToUpdate != null) {
                if (idProduct != 0) {
                    // Cập nhật quantityProduct nếu sản phẩm đã tồn tại trong giỏ hàng
                    cartToUpdate.setQuantityProduct(cartToUpdate.getQuantityProduct() + quantityProduct);
                }

                if (idTicket != 0) {
                    // Cập nhật quantityTicket nếu vé đã tồn tại trong giỏ hàng
                    cartToUpdate.setQuantityTicket(cartToUpdate.getQuantityTicket() + quantityTicket);
                }

                // Tính lại giá tiền của giỏ hàng sau khi cập nhật số lượng sản phẩm hoặc vé
                double totalProductPrice = (cartToUpdate.getProductEntity() != null) ? cartToUpdate.getProductEntity().getPrice() * cartToUpdate.getQuantityProduct() : 0;
                double totalTicketPrice = (cartToUpdate.getTicketEntity() != null) ? cartToUpdate.getTicketEntity().getPriceTicket() * cartToUpdate.getQuantityTicket() : 0;
                cartToUpdate.setPriceCart(totalProductPrice + totalTicketPrice);

                cartRepository.save(cartToUpdate);
            } else {
                // Nếu giỏ hàng chưa tồn tại, tạo mới giỏ hàng
                CartEntity cart = new CartEntity();
                cart.setQuantityProduct(quantityProduct);
                cart.setQuantityTicket(quantityTicket);
                UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new IOException("User with ID " + idUser + " not found."));
                cart.setUserEntity(user);

                if (idProduct != 0) {
                    ProductEntity product = productRepository.findById(idProduct).orElseThrow(() -> new IOException("Product with ID " + idProduct + " not found."));
                    cart.setProductEntity(product);
                }

                if (idTicket != 0) {
                    TicketEntity ticket = ticketRepository.findById(idTicket).orElseThrow(() -> new IOException("Ticket with ID " + idTicket + " not found."));
                    cart.setTicketEntity(ticket);
                }

                // Tính giá tiền của giỏ hàng
                double totalProductPrice = (cart.getProductEntity() != null) ? cart.getProductEntity().getPrice() * quantityProduct : 0;
                double totalTicketPrice = (cart.getTicketEntity() != null) ? cart.getTicketEntity().getPriceTicket() * quantityTicket : 0;
                cart.setPriceCart(totalProductPrice + totalTicketPrice);

                cartRepository.save(cart);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public List<CartResponse> getCartByIdUser(int idUser) {
        List<CartEntity> cartEntities = cartRepository.findAllByUserEntityId(idUser);
        List<CartResponse> cartResponses = new ArrayList<>();

        for (CartEntity cartEntity : cartEntities) {
            CartResponse cartResponse = new CartResponse();
            // Điều chỉnh phần này theo cấu trúc của CartResponse của bạn
            cartResponse.setIdCart(cartEntity.getId());
            cartResponse.setIdUser(cartEntity.getUserEntity().getId());
            cartResponse.setIdProduct(cartEntity.getProductEntity() != null ? cartEntity.getProductEntity().getId() : 0);
            cartResponse.setIdTicket(cartEntity.getTicketEntity() != null ? cartEntity.getTicketEntity().getId() : 0);
            cartResponse.setNameProduct(cartEntity.getProductEntity() != null ? cartEntity.getProductEntity().getName() : "");
            cartResponse.setNameTicket(cartEntity.getTicketEntity() != null ? cartEntity.getTicketEntity().getNameTicket() : "");
            cartResponse.setPriceProduct(cartEntity.getProductEntity() != null ? cartEntity.getProductEntity().getPrice() : 0.0);
            cartResponse.setPriceTicket(cartEntity.getTicketEntity() != null ? cartEntity.getTicketEntity().getPriceTicket() : 0.0);
            cartResponse.setQuantityProduct(cartEntity.getQuantityProduct());
            cartResponse.setQuantityTicket(cartEntity.getQuantityTicket());
            cartResponse.setTotalPrice(cartEntity.getPriceCart());

            cartResponse.setImageWorkshop(cartEntity.getTicketEntity().getWorkshop().getImageWorkshop());

            cartResponse.setNameWorkshop(cartEntity.getTicketEntity().getWorkshop().getNameWorkshop());
            cartResponses.add(cartResponse);
        }

        return cartResponses;
    }



    @Override
    @Transactional
    public boolean deleteCartById(int idCart) {
        try {
            // Kiểm tra xem giỏ hàng có tồn tại hay không
            if (cartRepository.existsById(idCart)) {
                // Nếu tồn tại, tiến hành xóa giỏ hàng
                cartRepository.deleteById(idCart);
                return true;
            } else {
                // Nếu không tồn tại, trả về false
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
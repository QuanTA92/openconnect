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
public class CarService implements CartServiceImp {

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
    public boolean insertCart(int quantityProduct, int quantityTicket, int idUser, int idProduct, int idTicket) throws IOException {
        try {
            CartEntity cart = new CartEntity();
            cart.setQuantityProduct(quantityProduct);
            cart.setQuantityTicket(quantityTicket);

            UserEntity user = userRepository.findById(idUser).orElse(null);
            if (user == null) {
                throw new IOException("User with ID " + idUser + " not found.");
            }
            cart.setUserEntity(user);

            double priceProduct = 0;
            if (idProduct != 0) {
                ProductEntity product = productRepository.findById(idProduct).orElse(null);
                if (product == null) {
                    throw new IOException("Product with ID " + idProduct + " not found.");
                }
                cart.setProductEntity(product); // Thiết lập tham chiếu đến ProductEntity
                priceProduct = product.getPrice() * quantityProduct;
            }

            double priceTicket = 0;
            if (idTicket != 0) {
                TicketEntity ticket = ticketRepository.findById(idTicket).orElse(null);
                if (ticket == null) {
                    throw new IOException("Ticket with ID " + idTicket + " not found.");
                }
                cart.setTicketEntity(ticket); // Thiết lập tham chiếu đến TicketEntity
                priceTicket = ticket.getPriceTicket() * quantityTicket;
            }

            cart.setPriceCart(priceProduct + priceTicket);

            cartRepository.save(cart);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CartResponse> getCartByIdUser(int idUser) {
        List<CartEntity> cartEntities = cartRepository.findByUserEntityId(idUser);
        List<CartResponse> cartResponses = new ArrayList<>();

        for (CartEntity cartEntity : cartEntities) {
            CartResponse cartResponse = new CartResponse();
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
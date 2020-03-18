package org.fasttrackit.onlineshop.service;


import org.fasttrackit.onlineshop.persistance.CartRepository;
import org.fasttrackit.onlineshop.transfer.cart.AddProductsToCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addProductToCart(AddProductsToCartRequest request){
        LOGGER.info("Adding products to cart: {}", request);

        cartRepository.findById(request.getCostumerId());
    }
}

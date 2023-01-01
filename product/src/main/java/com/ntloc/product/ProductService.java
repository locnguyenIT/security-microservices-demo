package com.ntloc.product;

import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ntloc.product.ProductConstant.PRODUCT_NOT_FOUND;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProduct() {
        List<ProductEntity> listProduct = productRepository.findAll();
        return productMapper.toListDTO(listProduct);
    }

    public ProductDTO getProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException(PRODUCT_NOT_FOUND));
        return productMapper.toDTO(product);
    }

    @Transactional
    public void updateQuantity(OrdersRequest ordersRequest) {
        ProductEntity product = productRepository.findById(ordersRequest.getProductId()).orElseThrow(() ->
                new NotFoundException(PRODUCT_NOT_FOUND));
        if (product.getQuantity() > ordersRequest.getQuantity()) {
            product.setQuantity(product.getQuantity() - ordersRequest.getQuantity());
        }
    }
}

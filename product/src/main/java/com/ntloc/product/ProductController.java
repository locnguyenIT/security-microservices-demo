package com.ntloc.product;

import com.ntloc.client.orders.OrdersRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.product.ProductConstant.URI_REST_API_VERSION_PRODUCT;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_PRODUCT)
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long id) {
        log.info("ProductId {}", id);
        return productService.getProduct(id);
    }

    @PutMapping
    public void updateQuantity(@RequestBody OrdersRequest ordersRequest) {
        log.info("Update quantity after orders success: {}", ordersRequest);
        productService.updateQuantity(ordersRequest);
    }

}

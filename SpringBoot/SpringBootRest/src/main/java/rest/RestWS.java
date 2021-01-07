package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Product;
import domain.ProductV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;

import java.util.concurrent.CompletableFuture;

@RestController
public class RestWS {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public CompletableFuture<ProductV1> getProduct() throws InterruptedException {
        CompletableFuture<ProductV1> result = productService.getProduct();
        return result;
    }

    private static Product getResource(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Product.class);
    }

    @GetMapping(value = "/product/id")
    public ProductV1 getProductV1() {
        return new ProductV1("1","test");
    }


}
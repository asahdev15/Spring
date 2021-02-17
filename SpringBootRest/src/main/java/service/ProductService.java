package service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import domain.ProductV1;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    private static ProductV1 product = new ProductV1("1", "Ashish");

    @Async
    public CompletableFuture<ProductV1> getProduct() throws InterruptedException {
        System.out.println("getProduct START");
        Thread.sleep(2000L); // Artificial delay of 2s for demonstration purposes
        System.out.println("getProduct END");
        return CompletableFuture.completedFuture(product);
    }

}

package client;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import domain.ProductV1;

public class TestClientApplication
{
   private static final String BASE_URL = "http://localhost:8080/products/";

   public static void main(String[] args)
   {
      asyncCall();
   }

   public static void syncCall() {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<ProductV1> result = restTemplate.getForEntity(BASE_URL, ProductV1.class);
      System.out.print(">>>>>> Fetching Value >>>>>> \n");
      System.out.println("ID - " + result.getBody().getId() + ", Name" + result.getBody().getName());
   }

   public static void asyncCall()  {
      AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
      ListenableFuture<ResponseEntity<ProductV1>> entity = asyncRestTemplate.getForEntity(BASE_URL, ProductV1.class);
      entity.addCallback(new ListenableFutureCallback<ResponseEntity<ProductV1>>() {
         @Override
         public void onFailure(Throwable ex) {
            System.out.println("Error");
            return;
         }
         @Override
         public void onSuccess(ResponseEntity<ProductV1> entity) {
            System.out.println("Success");
            return;
         }
      });
      System.out.print(">>>>>> Fetching Value >>>>>> \n");
   }


}
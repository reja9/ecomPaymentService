package dev.reja.paymentService.paymentService.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Component
public class OrderComponent {

    @Value("${order.update.url}")
    String updateOrderUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String updateOrder(UUID orderId){
        String url=updateOrderUrl+"?orderId="+orderId;
        log.info(url);
        HttpHeaders headers=new HttpHeaders();
        HttpEntity entity=new HttpEntity<String>(headers);
        ResponseEntity response=restTemplate.exchange(url, HttpMethod.PUT,entity,String.class);
        if(response.getStatusCode().value()==200){
            return "Order is successfully Updated";
        }
        return "Error in hitting api";
    }
}

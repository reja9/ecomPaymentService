package dev.reja.paymentService.paymentService.config;


import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazrpayClientConfiguration {

    @Value("${razorpay.client.key}")
    private String razorpayClientKey;
    @Value("${razorpay.client.secret}")
    private String razorpayClientSecret;
    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(razorpayClientKey, razorpayClientSecret);
        return razorpay;

    }
}

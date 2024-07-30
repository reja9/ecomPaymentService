package dev.reja.paymentService.paymentService.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.reja.paymentService.paymentService.config.RazrpayClientConfiguration;
import dev.reja.paymentService.paymentService.dtos.PaymentRquestDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RazrpayClientConfiguration razrpayClientConfiguration;
    @Override
    public String createPaymentLink(PaymentRquestDto paymentRquestDto) throws RazorpayException {
        RazorpayClient razorpay=razrpayClientConfiguration.getRazorpayClient();
        JSONObject paymentRquest=new JSONObject();

        paymentRquest.put("amount",paymentRquestDto.getAmount());
        paymentRquest.put("currency","INR");
        paymentRquest.put("accept_partial",false);
        paymentRquest.put("expire_by", Instant.now().toEpochMilli() + 600000);
        paymentRquest.put("reference_id", paymentRquestDto.getOrderId());
        paymentRquest.put("description", paymentRquestDto.getDescription());
        JSONObject customer = new JSONObject();
        customer.put("name", paymentRquestDto.getCustomerName());
        customer.put("contact",paymentRquestDto.getCustomerPhone());
        customer.put("email",paymentRquestDto.getCustomerEmail());
        paymentRquest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentRquest.put("notify",notify);
        paymentRquest.put("reminder_enable",true);
        PaymentLink payment = razorpay.paymentLink.create(paymentRquest);

        return payment.toString();
    }
}

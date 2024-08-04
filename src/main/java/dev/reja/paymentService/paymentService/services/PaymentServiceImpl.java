package dev.reja.paymentService.paymentService.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.reja.paymentService.paymentService.config.RazrpayClientConfiguration;
import dev.reja.paymentService.paymentService.dtos.PaymentRquestDto;
import dev.reja.paymentService.paymentService.models.Payment;
import dev.reja.paymentService.paymentService.models.PaymentStatus;
import dev.reja.paymentService.paymentService.repositories.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
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
        JSONObject paymentObj=new JSONObject(payment.toString());
        String refernce=paymentObj.getString("id").split("_")[1];
        log.info("referbce is "+refernce);
        Payment payment1=new Payment();
        payment1.setOrderId(UUID.fromString(paymentRquestDto.getOrderId()));
        payment1.setAmount(paymentRquestDto.getAmount());
        payment1.setUserId(paymentRquestDto.getUserId());
        payment1.setPaymentStatus(PaymentStatus.PENDING);
        payment1.setRefDesc(refernce);
        payment1=paymentRepository.save(payment1);

        return payment.toString();
    }
}

package dev.reja.paymentService.paymentService.services;

import dev.reja.paymentService.paymentService.components.OrderComponent;
import dev.reja.paymentService.paymentService.exceptions.PymentNotFoundException;
import dev.reja.paymentService.paymentService.models.Payment;
import dev.reja.paymentService.paymentService.models.PaymentStatus;
import dev.reja.paymentService.paymentService.repositories.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebhookServiceImpl implements WebhookService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderComponent orderComponent;
    @Override
    public String getOrderSuccess(String response) {
        log.info("request is "+response);

        JSONObject jsonObject=new JSONObject(response);

        String status=jsonObject.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity").getString("status");
        String reference=jsonObject.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity").getString("description").replace("#", "");
        log.info(reference);
        Payment payment=paymentRepository.findByRefDesc(reference).orElseThrow(
                ()-> new PymentNotFoundException("Payment is not found")
        );
        if(status.equals("captured")){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);

            orderComponent.updateOrder(payment.getOrderId());
            payment=paymentRepository.save(payment);
        }
        log.info(status);
        return status;
    }
}

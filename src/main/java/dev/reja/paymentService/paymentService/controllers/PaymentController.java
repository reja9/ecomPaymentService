package dev.reja.paymentService.paymentService.controllers;

import com.razorpay.RazorpayException;
import dev.reja.paymentService.paymentService.dtos.PaymentRquestDto;
import dev.reja.paymentService.paymentService.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity doPayment(@RequestBody PaymentRquestDto paymentRquestDto) throws RazorpayException {
        return ResponseEntity.ok(paymentService.createPaymentLink(paymentRquestDto));
    }
}

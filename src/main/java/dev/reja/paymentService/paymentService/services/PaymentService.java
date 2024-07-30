package dev.reja.paymentService.paymentService.services;

import com.razorpay.RazorpayException;
import dev.reja.paymentService.paymentService.dtos.PaymentRquestDto;

public interface PaymentService {

    public String createPaymentLink(PaymentRquestDto paymentRquestDto) throws RazorpayException;
}

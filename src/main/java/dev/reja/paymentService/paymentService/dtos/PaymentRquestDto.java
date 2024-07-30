package dev.reja.paymentService.paymentService.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRquestDto {

    private BigDecimal amount;
    private String orderId;
    private String description;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
}

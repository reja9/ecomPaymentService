package dev.reja.paymentService.paymentService.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Currency extends BaseModel {
    private String currencyName;
    private String currencyCode;
    private String country;
}

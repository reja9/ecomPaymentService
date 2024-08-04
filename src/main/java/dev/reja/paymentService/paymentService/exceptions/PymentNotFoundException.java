package dev.reja.paymentService.paymentService.exceptions;

public class PymentNotFoundException extends RuntimeException {
    public PymentNotFoundException (String message){
        super(message);
    }
}

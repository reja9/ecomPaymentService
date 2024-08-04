package dev.reja.paymentService.paymentService.controllers;

import dev.reja.paymentService.paymentService.services.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhooController {
    @Autowired
    private WebhookService webhookService;

    @PostMapping
    public ResponseEntity getOrderSuccessResp(@RequestBody String response){

        return ResponseEntity.ok(webhookService.getOrderSuccess(response));
    }
}

package dev.reja.paymentService.paymentService.repositories;

import dev.reja.paymentService.paymentService.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByRefDesc(String reference);

}

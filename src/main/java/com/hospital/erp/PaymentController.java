package com.hospital.erp;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient/payment")
public class PaymentController {

    private final BillService billService;
    private final PaymentService paymentService;

    public PaymentController(BillService billService,
                             PaymentService paymentService) {
        this.billService = billService;
        this.paymentService = paymentService;
    }

    // =====================================
    // PROCESS PAYMENT
    // =====================================

    @PostMapping("/process")
    public String processPayment(@RequestParam("billId") Long billId,
                                 @RequestParam("paymentMethod") String paymentMethod,
                                 Model model) {

        Bill bill = billService.getBillById(billId)
                .orElseThrow(() ->
                        new RuntimeException("Bill Not Found"));

        // Create Payment Record
        Payment payment = new Payment();

        payment.setBillId(bill.getId());
        payment.setAmount(bill.getAmount());
        payment.setPaymentMethod(paymentMethod);

        payment.setTransactionId(
                "TXN-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8)
        );

        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());

        // Save Payment
        paymentService.savePayment(payment);

        // Update Bill Status
        bill.setPaymentStatus("PAID");
        billService.updateBill(bill);

        model.addAttribute("payment", payment);

        return "payment-success";
    }
}
package com.groupchat.Groupchat.controller;

import com.groupchat.Groupchat.config.RazorpayService;
import com.groupchat.Groupchat.models.DonationRequest;
import com.razorpay.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5175")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping("/createOrder")
    public ResponseEntity<Object> createOrder(@RequestBody DonationRequest donationRequest) {
        try {
            int amount = donationRequest.getAmount();
            String currency = donationRequest.getCurrency();

            // Create the Razorpay order
            Order order = razorpayService.createOrder(amount, currency);

            // Extract necessary fields from the Order object
            Map<String, Object> response = new HashMap<>();
            response.put("id", order.get("id"));
            response.put("amount", order.get("amount"));
            response.put("currency", order.get("currency"));
            response.put("status", order.get("status"));

            // Return the response as JSON
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating Razorpay order: " + e.getMessage());
        }
    }
}

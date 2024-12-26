package com.groupchat.Groupchat.config;

import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    private RazorpayClient razorpayClient;

    // Constructor to initialize Razorpay Client with API Keys
    public RazorpayService() {
        try {
            String KEY_ID = "rzp_test_CxeWDJfQVjWvwB";  // Replace with your Razorpay Key ID
            String KEY_SECRET = "FHKHllH2mMEQmDzflxAiyFBY";  // Replace with your Razorpay Secret

            this.razorpayClient = new RazorpayClient(KEY_ID, KEY_SECRET);
        } catch (Exception e) {
            System.out.println("Error initializing Razorpay client: " + e.getMessage());
        }
    }

    // Method to create Razorpay order
    public Order createOrder(int amount, String currency) throws Exception {
        try {
            int amountInPaise = amount * 100;  // Razorpay expects amount in paise (1 INR = 100 paise)
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amountInPaise); // Order amount in paise
            orderRequest.put("currency", currency);  // Currency type
            orderRequest.put("payment_capture", 1);  // Auto capture payment after authorization

            return razorpayClient.orders.create(orderRequest);  // Create order on Razorpay
        } catch (Exception e) {
            throw new Exception("Error creating Razorpay order: " + e.getMessage());
        }
    }
}

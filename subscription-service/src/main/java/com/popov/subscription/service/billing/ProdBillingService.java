package com.popov.subscription.service.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdBillingService implements BillingService {

    @Override
    public boolean processPayment(Long userId, double price) {
        try {
            // In a real implementation, you would:
            // 1. Get payment method from user
            // 2. Validate payment details
            // 3. Process through payment gateway
            // 4. Handle response

            // Mock implementation
            if (price <= 0) {
                return true; // Free subscriptions always succeed
            }

            // Simulate payment processing with 90% success rate
            return Math.random() < 0.9;

        } catch (Exception e) {
            // Log error
            return false;
        }
    }

}

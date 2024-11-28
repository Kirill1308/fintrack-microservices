package com.popov.subscription.service.billing;

public interface BillingService {

    boolean processPayment(Long userId, double price);

}

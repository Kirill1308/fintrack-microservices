package com.popov.subscription.service.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DevBillingServiceImpl implements BillingService {

    @Override
    public boolean processPayment(Long userId, double price) {
        return true;
    }

}

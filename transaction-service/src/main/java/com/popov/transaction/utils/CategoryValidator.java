package com.popov.transaction.utils;

import com.popov.exception.custom.CustomCategoryNotAllowedException;
import com.popov.transaction.client.subscription.SubscriptionTierClient;
import com.popov.transaction.constant.CategoryType;
import com.popov.transaction.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryValidator {

    private final SubscriptionTierClient subscriptionClient;

    public void validateCategoryAccess(Category category, Long userId) {
        if (category.getType() == CategoryType.CUSTOM) {
            var tier = subscriptionClient.getSubscriptionTier(userId);
            if (!tier.isCustomizable()) {
                throw new CustomCategoryNotAllowedException();
            }
        }
    }

}

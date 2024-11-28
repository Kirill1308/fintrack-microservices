package com.popov.subscription.mapper;

import com.popov.subscription.dto.SubscriptionRequest;
import com.popov.subscription.dto.SubscriptionResponse;
import com.popov.subscription.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponse toResponse(Subscription subscription);

    Subscription toEntity(SubscriptionRequest subscriptionRequest);

}

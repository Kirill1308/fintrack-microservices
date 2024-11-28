package com.popov.transaction.mapper;

import com.popov.transaction.dto.TransactionRequest;
import com.popov.transaction.dto.TransactionResponse;
import com.popov.transaction.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(TransactionRequest request);

    TransactionResponse toResponse(Transaction entity);

}

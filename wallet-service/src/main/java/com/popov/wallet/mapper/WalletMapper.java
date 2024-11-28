package com.popov.wallet.mapper;

import com.popov.wallet.dto.WalletRequest;
import com.popov.wallet.dto.WalletResponse;
import com.popov.wallet.entity.Wallet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    Wallet toEntity(WalletRequest request);

    WalletResponse toResponse(Wallet entity);

    List<WalletResponse> toResponse(List<Wallet> entities);

}

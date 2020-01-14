package com.alenia.kata.bank.api.mapper;

import com.alenia.kata.bank.api.payload.TransferResponse;
import com.alenia.kata.bank.domain.entity.Transfer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    @Mapping(source = "payer.id", target = "payer")
    @Mapping(source = "payee.id", target = "payee")
    TransferResponse toTransferResponse(Transfer transfer);

    @IterableMapping(elementTargetType = TransferResponse.class)
    List<TransferResponse> toTransfersResponse(List<Transfer> transfers);
}

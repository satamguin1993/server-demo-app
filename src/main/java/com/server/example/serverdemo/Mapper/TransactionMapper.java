package com.server.example.serverdemo.Mapper;

import com.server.example.serverdemo.Model.Item;
import com.server.example.serverdemo.Model.Transaction;
import com.server.example.serverdemo.Api.model.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mappings({
        @Mapping(target="itemList", source = "itemList"),
    })
    Transaction mapToTransactionEntity(TransactionRequest transactionRequest, List<Item> itemList);

    @Mappings({
        @Mapping(target="itemIds", expression = "java(getItemIds(transaction.getItemList()))"),
    })
    TransactionRequest mapToTransactionRequest(Transaction transaction);

    default List<Integer> getItemIds(List<Item> itemList) {
        return itemList.stream().map(item -> item.getId()).collect(Collectors.toList());
    }
}

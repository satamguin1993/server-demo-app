package com.server.example.serverdemo.Mapper;

import com.server.example.serverdemo.Api.Requests.ItemDetail;
import com.server.example.serverdemo.Entity.Transaction;
import com.server.example.serverdemo.Api.Requests.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mappings({
        @Mapping(target="itemDetails", source = "itemDetails")
    })
    Transaction mapToTransactionEntity(TransactionRequest transactionRequest,
                                       List<com.server.example.serverdemo.Entity.ItemDetail> itemDetails);

    @Mappings({
        @Mapping(target = "customerId", source = "customerId"),
        @Mapping(target = "itemUniqueId", expression = "java(getItemUniqueId())")
    })
    com.server.example.serverdemo.Entity.ItemDetail mapToItemDetailEntity(
            ItemDetail itemDetail, Integer customerId);

    @Mappings({
        @Mapping(target="itemDetails", expression = "java(getItemDetailRequestList(transaction.getItemDetails()))")
    })
    TransactionRequest mapToTransactionRequest(Transaction transaction);


    default UUID getItemUniqueId() {
        return UUID.randomUUID();
    }

    default List<ItemDetail> getItemDetailRequestList(
            List<com.server.example.serverdemo.Entity.ItemDetail> itemDetailList) {

        List<ItemDetail> itemDetails = new ArrayList<>();

        itemDetailList.stream().forEach(item -> {
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.setItemId(item.getItemId());
            itemDetail.setItemBought(item.getItemBought());
            itemDetail.setPricePerUnit(item.getPricePerUnit());
            itemDetail.setItemUniqueId(item.getItemUniqueId());
            itemDetails.add(itemDetail);
        });
        return itemDetails;
    }
}

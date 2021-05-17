package com.server.example.serverdemo.Mapper;

import com.server.example.serverdemo.Entity.Item;
import com.server.example.serverdemo.Api.Requests.ItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item mapToItemEntity(ItemRequest itemRequest);

    ItemRequest mapToItemRequest(Item item);

}


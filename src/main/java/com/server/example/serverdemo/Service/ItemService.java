package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.Requests.TransactionRequest;
import com.server.example.serverdemo.Entity.ItemDetail;
import com.server.example.serverdemo.Exception.ItemNotFoundException;
import com.server.example.serverdemo.Exception.ItemValidationException;
import com.server.example.serverdemo.Entity.Item;
import com.server.example.serverdemo.Repository.ItemRepository;
import com.server.example.serverdemo.Api.Requests.ItemRequest;
import com.server.example.serverdemo.Api.Requests.ValidationResult;
import com.server.example.serverdemo.Mapper.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemValidationService itemValidationService;

    @Autowired
    private ItemRepository itemRepository;

    public ItemRequest createItemRequest(ItemRequest itemRequest) {

        ValidationResult validationResult = itemValidationService.validateCreateItemRequest(itemRequest);

        if (!validationResult.hasError()) {
            logger.info("Validation passed for create item request");
            Item item = ItemMapper.INSTANCE.mapToItemEntity(itemRequest);
            item = itemRepository.save(item);
            logger.info("Item successfully registered into database with itemId={}", item.getId());
            itemRequest = ItemMapper.INSTANCE.mapToItemRequest(item);
        } else {
            logger.error("Validation failed for creating new item request");
            throw new ItemValidationException(validationResult);
        }

        return itemRequest;
    }

    public ItemRequest getItemById(Integer itemId) {

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        ItemRequest itemRequest;
        if (optionalItem.isPresent()) {
            logger.info("Item found for the provided itemId={}", itemId);
            itemRequest = ItemMapper.INSTANCE.mapToItemRequest(optionalItem.get());
        } else {
            logger.error("No item found in the system with the given itemId={}", itemId);
            throw new ItemNotFoundException(itemId);
        }
        return itemRequest;
    }

    public List<ItemRequest> getAllItems(Optional<String> status) {

        List<ItemRequest> itemRequests = new ArrayList<>();
        Iterator<Item> itemItr = null;
        if (status.isPresent()) {
            logger.info("Fetching all the items based on filter params");
            Item.Status itemStatus = Item.Status.valueOf(status.get());
            //itemItr = itemRepository.findItemByStatus(itemStatus).iterator();
        } else {
            logger.info("Fetching all the items from the system");
            itemItr = itemRepository.findAll().iterator();
        }

        while (itemItr.hasNext()) {
            Item item = itemItr.next();
            ItemRequest itemRequest = ItemMapper.INSTANCE.mapToItemRequest(item);
            itemRequests.add(itemRequest);
        }

        if (itemRequests.isEmpty())
            logger.warn("No item is found in the system");

        return itemRequests;
    }

    public ItemRequest updateItemRequest(int itemId, ItemRequest request) {

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        ItemRequest itemRequest;
        if (optionalItem.isPresent()) {
            logger.info("Item found for the provided itemId={}", itemId);
            itemRequest = ItemMapper.INSTANCE.mapToItemRequest(optionalItem.get());
            itemRequest = ItemMapper.INSTANCE.mapToItemRequest(
                            itemRepository.save(
                            ItemMapper.INSTANCE.mapToItemEntity(itemRequest)));
            logger.info("Updated item details for the itemId={}", itemId);
        } else {
            logger.warn("No Item is found with itemId={}", itemId);
            throw new ItemNotFoundException(itemId);
        }

        return itemRequest;
    }

    public void updateItemDetailsBeforeCheckout(TransactionRequest request, List<ItemDetail> itemDetails) {
        logger.info("Update item details for checkout for transactionId={} customerId={}",
                request.getTransactionId(), request.getCustomerId());

        itemDetails.stream().forEach(itemDetail -> {
            Optional<Item> optionalItem = itemRepository.findById(itemDetail.getItemId());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                Integer itemsBought = itemDetail.getItemBought();

                if (item.getMaxUnitAvailablePerCustomer() >= itemsBought) {
                    if (item.getMaxUnitAvailablePerCustomer() == itemsBought) {
                        item.setStatus(Item.Status.SOLD_OUT);
                    }
                    Integer availableUnits =  item.getAvailableUnits() - itemsBought;
                    item.setAvailableUnits(availableUnits);
                    Integer maxUnitAvailable = item.getMaxUnitAvailablePerCustomer();
                    item.setMaxUnitAvailablePerCustomer(
                            availableUnits >= maxUnitAvailable ? maxUnitAvailable : availableUnits);
                    Integer soldSoFar = item.getSoldSoFar();
                    item.setSoldSoFar(soldSoFar + itemsBought);
                }
                itemRepository.save(item);
            }
        });
    }
}

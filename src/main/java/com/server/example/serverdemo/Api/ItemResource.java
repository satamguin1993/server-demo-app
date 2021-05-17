package com.server.example.serverdemo.Api;

import com.server.example.serverdemo.Api.Requests.ItemRequest;
import com.server.example.serverdemo.Service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemResource {

    private static final Logger logger = LoggerFactory.getLogger(ItemResource.class);

    @Autowired
    private ItemService itemService;

    @PostMapping("/v1/items/")
    public ResponseEntity<ItemRequest> createItems(@RequestBody ItemRequest itemRequest) {
        logger.info("Received request for creating new Item");
        return ResponseEntity.ok(itemService.createItemRequest(itemRequest));
    }

    @GetMapping("/v1/items/")
    public ResponseEntity<List<ItemRequest>> getAllItems(@RequestParam("status") Optional<String> status) {
        logger.info("Request received to fetch all the items");
        List<ItemRequest> getAllItemsRequest = itemService.getAllItems(status);
        logger.info("All the items are retrieved from the DB");
        return ResponseEntity.ok(getAllItemsRequest);
    }

    @GetMapping("/v1/items/{itemId}")
    public ResponseEntity<ItemRequest> getItemById(@PathVariable(value = "itemId") Integer itemId) {
        logger.info("ItemRequest Param or Query Param Id sent from client for itemId={}", itemId);
        ItemRequest itemRequest = itemService.getItemById(itemId);
        logger.info("Item Retrieved from the DB for itemId={}", itemId);
        return ResponseEntity.ok(itemRequest);
    }

    @PatchMapping("/v1/items/{itemId}")
    public ResponseEntity<ItemRequest> updateItemById(@PathVariable("itemId") Integer itemId,
                                                      @RequestBody ItemRequest itemRequest) {
        logger.info("Request received to update Item for itemId={}", itemId);
        itemRequest = itemService.updateItemRequest(itemId, itemRequest);
        logger.info("Update successfully completed for itemId={}", itemId);
        return ResponseEntity.ok(itemRequest);
    }

}

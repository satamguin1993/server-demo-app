package com.server.example.serverdemo.Api;

import com.server.example.serverdemo.Api.model.ItemRequest;
import com.server.example.serverdemo.Service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class ItemResource {

    private static final Logger logger = LoggerFactory.getLogger(ItemResource.class);

    @Autowired
    private ItemService itemService;

    @PostMapping("/v1/items/")
    public ResponseEntity<ItemRequest> createItems(@RequestBody ItemRequest itemRequest) {
        logger.info("Received request for creating book");
        return ResponseEntity.ok(itemService.createItemRequest(itemRequest));
    }

    @GetMapping("/v1/items/")
    public ResponseEntity<List<ItemRequest>> getAllItems(@RequestParam("status") Optional<String> status) {
        logger.info("Request received to fetch all the items");
        List<ItemRequest> getAllBooksRequest = itemService.getAllItems(status);
        logger.info("Books Retrieved from the DB");
        return ResponseEntity.ok(getAllBooksRequest);
    }

    @GetMapping("/v1/items/{itemId}")
    public ResponseEntity<ItemRequest> getItemById(@PathVariable(value = "itemId") Integer itemId) {
        logger.info("ItemRequest Param or Query Param Id sent from client for itemId={}", itemId);
        ItemRequest itemRequest = itemService.getItemById(itemId);
        logger.info("Employee Retrieved from the DB for itemId={}", itemId);
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

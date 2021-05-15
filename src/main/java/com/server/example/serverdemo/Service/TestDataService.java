package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Model.Brand;
import com.server.example.serverdemo.Model.Category;
import com.server.example.serverdemo.Model.Item;
import com.server.example.serverdemo.Repository.BrandRepository;
import com.server.example.serverdemo.Repository.CategoryRepository;
import com.server.example.serverdemo.Repository.ItemRepository;
import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TestDataService {

    private static final Logger logger = LoggerFactory.getLogger(TestDataService.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void createTestData() {

        Iterable<Brand> brandItr = createAndSaveBrandDetails();
        Iterable<Category> categoryItr = createAndSaveCategoryDetails();
        createAndSaveItemDetails(brandItr, categoryItr);
        logger.info("Test data saved in the db for testing");
    }

    private void createAndSaveItemDetails(Iterable<Brand> brandItr, Iterable<Category> categoryItr) {

        logger.info("Saving item details!!!");
        EasyRandom generator = new EasyRandom();
        List<Item> itemDetailsList = generator.objects(Item.class, 20)
                .collect(Collectors.toList());
        Integer brandId = brandItr.iterator().next().getId();
        Integer categoryId = categoryItr.iterator().next().getId();
        Random rd = new Random();
        itemDetailsList.stream().forEach(item -> {
            item.setBrandId(brandId);
            item.setCategoryId(categoryId);
            item.setPrice(rd.nextFloat()*1000);
            item.setAvailableCopies(rd.nextInt(10) + rd.nextInt(10));
            item.setMaxUnitAvailablePerCustomer(rd.nextInt(3) + 2);
        });
        itemRepository.saveAll(itemDetailsList);
    }

    private Iterable<Category> createAndSaveCategoryDetails() {

        logger.info("Saving category details!!!");
        EasyRandom generator = new EasyRandom();
        List<Category> categoryList = generator.objects(Category.class, 4)
                .collect(Collectors.toList());
        return categoryRepository.saveAll(categoryList);
    }

    private Iterable<Brand> createAndSaveBrandDetails() {
        logger.info("Saving brand details!!!");
        EasyRandom generator = new EasyRandom();
        List<Brand> brandList = generator.objects(Brand.class, 5)
                .collect(Collectors.toList());
        return brandRepository.saveAll(brandList);
    }

}

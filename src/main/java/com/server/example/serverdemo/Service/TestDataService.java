package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.Requests.CustomerRequest;
import com.server.example.serverdemo.Entity.Address;
import com.server.example.serverdemo.Entity.Brand;
import com.server.example.serverdemo.Entity.Category;
import com.server.example.serverdemo.Entity.Item;
import com.server.example.serverdemo.Mapper.CustomerMapper;
import com.server.example.serverdemo.Repository.*;
import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void createTestData() {

        createAndSaveBrandDetails();
        createAndSaveCategoryDetails();
        createAndSaveItemDetails();
        createAndSaveAddressDetails();
        //createAndSaveCustomerDetails();
        logger.info("Test data saved in the db for testing");
    }

    private void createAndSaveAddressDetails() {

        EasyRandom generator = new EasyRandom();
        List<Address> addressList = generator.objects(Address.class, 20)
                .collect(Collectors.toList());
        addressRepository.saveAll(addressList);
    }

    private void createAndSaveItemDetails() {

        logger.info("Saving item details!!!");
        EasyRandom generator = new EasyRandom();
        Random idGenerator = new Random();

        List<Item> itemDetailsList = generator.objects(Item.class, 20)
                .collect(Collectors.toList());

        Random rd = new Random();
        itemDetailsList.stream().forEach(item -> {
            item.setBrandId(idGenerator.nextInt(10));
            item.setCategoryId(idGenerator.nextInt(10) + 10);
            item.setPrice(rd.nextFloat()*1000);
            item.setAvailableUnits(rd.nextInt(20) + rd.nextInt(10));
            item.setSoldSoFar(rd.nextInt(100) + 20);
            item.setMaxUnitAvailablePerCustomer(rd.nextInt(3) + 2);
            item.setStatus(Item.Status.AVAILABLE);
        });
        itemRepository.saveAll(itemDetailsList);
    }

    private Iterable<Category> createAndSaveCategoryDetails() {

        logger.info("Saving category details!!!");
        EasyRandom generator = new EasyRandom();
        List<Category> categoryList = generator.objects(Category.class, 10)
                .collect(Collectors.toList());
        return categoryRepository.saveAll(categoryList);
    }

    private Iterable<Brand> createAndSaveBrandDetails() {
        logger.info("Saving brand details!!!");
        EasyRandom generator = new EasyRandom();
        List<Brand> brandList = generator.objects(Brand.class, 10)
                .collect(Collectors.toList());
        return brandRepository.saveAll(brandList);
    }

    public CustomerRequest createCustomerRequest(CustomerRequest customerRequest) {

        customerRequest.setCreated(new Date());
        return CustomerMapper.INSTANCE.mapToCustomerRequest(
                customerRepository.save(
                        CustomerMapper.INSTANCE.mapToCustomerEntity(customerRequest)));

    }
}

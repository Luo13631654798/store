package com.lyc.service;

import com.lyc.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressServiceTest {

    @Autowired
    private AddressService addressService;
    @Value("${user.address.max_count}")
    private Integer maxCount;
    @Test
    void addNewAddress() {
        Address address = new Address();
        address.setPhone("13511111111");
        address.setName("testname");
        addressService.addNewAddress(14,address);
    }
}
package com.lyc.mapper;

import com.lyc.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;
    @Test
    void insert() {
        Address address = new Address();
        address.setUid(24);
        address.setPhone("13511111111");
        address.setName("testname");
        addressMapper.insert(address);
    }

    @Test
    void countByUid() {
        System.out.println(addressMapper.countByUid(24));
    }

    @Test
    void findById() {
        System.out.println(addressMapper.findByUid(14));
    }

    @Test
    void setNonDefaultByUid() {
        addressMapper.setNonDefaultByUid(14);
    }

    @Test
    void setDefault() {
        addressMapper.setDefault(6,14);
    }

    @Test
    void delete() {
        addressMapper.delete(2);
    }

    @Test
    void findMaxAid() {
        System.out.println(addressMapper.findMaxAid(14));
    }
}
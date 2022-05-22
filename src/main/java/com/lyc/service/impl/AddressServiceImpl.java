package com.lyc.service.impl;

import com.lyc.domain.Address;
import com.lyc.mapper.AddressMapper;
import com.lyc.mapper.DistrictMapper;
import com.lyc.service.AddressService;
import com.lyc.service.DistrictService;
import com.lyc.service.ex.AddressCountLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DistrictService districtService;
    @Value("${user.address.max_count}")
    private Integer maxCount;
    @Override
    public int addNewAddress(Integer uid, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if (count>=maxCount){
            throw new AddressCountLimitException("地址数达到上限");
        }
        address.setUid(uid);
        address.setIsDefault(count==0?1:0);
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));

        return addressMapper.insert(address);
    }

    @Override
    public List<Address> findByUid(Integer id) {
        return addressMapper.findByUid(id);
    }

    @Override
    public int setDefaultAddress(Integer aid, Integer uid) {
        addressMapper.setNonDefaultByUid(uid);
        return addressMapper.setDefault(aid,uid);
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid) {
        Integer isDefault = addressMapper.findByAid(aid).getIsDefault();
        addressMapper.delete(aid);
        if (addressMapper.countByUid(uid)==0||isDefault==0){
            return;
        }
        Address maxAid = addressMapper.findMaxAid(uid);
        addressMapper.setDefault(maxAid.getAid(),uid);

    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        return addressMapper.findByAid(aid);
    }
}

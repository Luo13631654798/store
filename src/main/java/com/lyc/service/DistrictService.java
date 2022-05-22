package com.lyc.service;

import com.lyc.domain.District;

import java.util.List;

public interface DistrictService {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 区域信息
     */
    public List<District> getByParent(String parent);

    /**
     * 根据code代号查询区域名
     * @param code code代号
     * @return 区域名
     */
    public String getNameByCode(String code);
}

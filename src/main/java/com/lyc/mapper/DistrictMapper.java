package com.lyc.mapper;

import com.lyc.domain.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 某个父代号下的所有列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据code查询区域名
     * @param code code代号
     * @return 对应区域名称
     */
    public String findNameByCode(String code);
}

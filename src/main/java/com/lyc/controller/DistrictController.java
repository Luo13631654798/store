package com.lyc.controller;

import com.lyc.domain.District;
import com.lyc.service.DistrictService;
import com.lyc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{
    @Autowired
    private DistrictService districtService;
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        return new JsonResult<List<District>>(OK,districtService.getByParent(parent));
    }
}

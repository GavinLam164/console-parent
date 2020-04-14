package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import top.mall.pojo.RpcResult;
import top.mall.pojo.SpecGroup;
import top.mall.pojo.SpecValue;
import top.mall.product.service.SpecGroupService;
import top.mall.product.service.SpecValueService;

import java.util.List;

@RequestMapping("/spec")
@RestController
public class SpecificationController {

    @Reference
    SpecGroupService specGroupService;

    @Reference
    SpecValueService specValueService;

    @RequestMapping("/group/list")
    public RpcResult<List<SpecGroup>> groupList() {
        return RpcResult.success(specGroupService.specGroupList());
    }

    @RequestMapping("/group/list/select")
    public RpcResult<List<SpecGroup>> groupListSelect(@RequestParam("spuId")Integer spuId) {
        return RpcResult.success(specGroupService.specGroupListSelectBySpuId(spuId));
    }

    @RequestMapping(value = "/value/add", method = RequestMethod.POST)
    public RpcResult<Void> valueAdd(
            @RequestParam("specGroupId") Integer specGroupId,
            @RequestParam("specValueName") String specValueName,
            @RequestParam("specValueIndex") Integer specValueIndex
            ) {
        SpecValue specValue = new SpecValue(null, specGroupId, specValueName, specValueIndex);
        specValueService.valueAdd(specValue);
        return RpcResult.success(null);
    }

    @RequestMapping(value ="/value/del", method = RequestMethod.POST)
    public RpcResult<Void> valueDel(
            @RequestParam("skuId") Integer skuId,
            @RequestParam("specValueId") Integer specValueId
    ) {
        specValueService.valueDel(skuId, specValueId);
        return RpcResult.success(null);
    }
}

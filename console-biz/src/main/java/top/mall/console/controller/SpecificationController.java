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
    public RpcResult<Void> valueAdd(@RequestBody SpecValue specValue) {
        specValueService.valueAdd(specValue);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/value/list", method = RequestMethod.GET)
    public RpcResult<List<SpecValue>> valueList(@RequestParam("spuId")Integer spuId, @RequestParam("specGroupId") Integer specGroupId) {
        return RpcResult.success(specValueService.valueList(spuId, specGroupId));
    }
    @RequestMapping(value ="/value/del", method = RequestMethod.POST)
    public RpcResult<Void> valueDel(
            @RequestParam("spuId") Integer spuId,
            @RequestParam("specValueId") Integer specValueId
    ) {
        specValueService.valueDel(spuId, specValueId);
        return RpcResult.success(null);
    }
}

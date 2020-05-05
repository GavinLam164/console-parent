package top.mall.product.service;

import org.apache.ibatis.annotations.Param;
import top.mall.pojo.SpecSku;

import java.util.List;

public interface SpecSkuService {
    List<SpecSku> selectSpecSkuBySpuId(@Param("spuId")Integer spuId);
}

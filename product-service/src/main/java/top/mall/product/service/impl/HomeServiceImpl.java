package top.mall.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.mall.console.utils.manager.cache.RedisCacheManager;
import top.mall.dao.mapper.HomeConfigMapper;
import top.mall.dao.mapper.ProductSpuImageMapper;
import top.mall.dao.mapper.ProductSpuMapper;
import top.mall.pojo.HomeConfig;
import top.mall.pojo.ProductSpu;
import top.mall.pojo.ProductSpuImage;
import top.mall.product.service.HomeService;
import top.mall.product.service.ProductService;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    ProductService productService;
    @Resource
    HomeConfigMapper homeConfigMapper;
    @Resource
    ProductSpuMapper productSpuMapper;
    @Resource
    ProductSpuImageMapper productSpuImageMapper;
    @Resource
    RedisCacheManager redisCacheManager;
    @Override
    public Map<String, List> findAllConfigs() {
        String json = redisCacheManager.get("homeConfig");
        Map<String, List> res = new HashMap<>();
        if(json == null || json.length() == 0) {
            List<HomeConfig> configs = homeConfigMapper.selectAll();
            Map<Integer, List<HomeConfig>> map = configs.stream().collect(Collectors.groupingBy(HomeConfig::getType));
            res.put("bannerList", map.get(0));
            res.put("iconList", map.get(1));
            res.put("spuList", findSpuList(map.get(2)));
            json = JSONObject.toJSONString(res);
            redisCacheManager.set("homeConfig", json, 3000);
        }else {
            res = (Map<String, List>) JSONObject.parse(json);
        }
        return res;
    }

    private List findSpuList(List<HomeConfig> homeConfigs) {
        if(homeConfigs == null) return null;
        for(HomeConfig config: homeConfigs) {
            String value = config.getValue();
            List<Integer> collect = Arrays.stream(value.split(",")).map(v -> Integer.valueOf(v)).collect(Collectors.toList());
            List<ProductSpu> list = productSpuMapper.findSelectSpuList(collect);
            list.forEach(spu -> {
                spu.setSkuImage(
                        productSpuImageMapper.selectByType(spu.getSpuId(), ProductSpuImage.SpuImageType.BANNER.getValue()).get(0));
            });
            config.setSpuList(list);
        }
        return homeConfigs;
    }
}

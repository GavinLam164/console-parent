package top.mall.user.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Service;
import top.mall.console.utils.manager.cache.RedisCacheManager;
import top.mall.dao.mapper.AddressMapper;
import top.mall.dao.mapper.AreaMapper;
import top.mall.pojo.*;
import top.mall.user.service.AreaService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    AreaMapper areaMapper;
    @Resource
    RedisCacheManager redisCacheManager;
    @Resource
    AddressMapper addressMapper;

    @Override
    public List<AreaTree> findAllArea() {
        String areaJson = redisCacheManager.get("area");
        List<AreaTree> treeList;
        if(areaJson == null){
            List<AreaTree> list = areaMapper.findAllArea();
            Map<String, AreaTree> fatherMap = new HashMap<String, AreaTree>();
            for (AreaTree c : list) {
                fatherMap.put(c.getAreaId(), c);
            }
            treeList = new ArrayList<AreaTree>();
            for (AreaTree c : list) {
                AreaTree father = fatherMap.get(c.getParentAreaId());
                if (father == null) {
                    treeList.add(c);
                } else {
                    father.getSubList().add(c);
                }
            }
            redisCacheManager.set("area", JSON.toJSONString(treeList));
        }else {
            treeList = JSON.parseArray(areaJson, AreaTree.class);
        }
        return treeList;
    }

    @Override
    public void addAddress(Address address,String token) {
        Integer uid = getUser(token).getUid();
        if(address.getAddressId() == null){
            address.setUid(uid);
            address.setSelect(1);
            addressMapper.insert(address);
        }else {
            addressMapper.updateByPrimaryKeySelective(address);
        }
        setSelectAddress(token, address.getAddressId());
    }

    public User getUser(String token) {
        String userInfoJson = redisCacheManager.get(token);
        User user = JSON.parseObject(userInfoJson, User.class);
        return user;
    }

    @Override
    public Map currentSelectAddress(String token) {
        Map<String, Object> map = new HashMap<>();
        Integer uid = getUser(token).getUid();
        List<Address> addresses = addressMapper.selectByUid(uid, 1);
        if(addresses == null || addresses.size() == 0) return map;
        Address address = addresses.get(0);
        if(address == null) return map;
        return getAddressAndAreaMap(address);
    }

    private Map getAddressAndAreaMap(Address address) {
        Area area = areaMapper.selectByPrimaryKey(address.getAreaId());
        Map<String, Object> map = new HashMap<>();
        map.put("address", address);
        map.put("area", area);
        return map;
    }

    @Override
    public List<Map> addressList(String token) {
        User user = getUser(token);
        Integer uid = user.getUid();
        List<Address> address = addressMapper.selectByUid(uid, null);
        return address.stream().map(v-> getAddressAndAreaMap(v)).collect(Collectors.toList());
    }

    @Override
    public Map addressDetail(Integer addressId) {
        return getAddressAndAreaMap(addressMapper.selectByPrimaryKey(addressId));
    }

    @Override
    public void setSelectAddress(String token, Integer addressId) {
        User user = getUser(token);
        addressMapper.resetSelect(user.getUid());
        Address address = new Address();
        address.setSelect(1);
        address.setAddressId(addressId);
        addressMapper.updateByPrimaryKeySelective(address);
    }
}

package top.mall.user.service;

import top.mall.pojo.Address;
import top.mall.pojo.AreaTree;

import java.util.List;
import java.util.Map;

public interface AreaService {
    List<AreaTree> findAllArea();

    void addAddress(Address address, String token);

    Map currentSelectAddress(String token);

    List<Map> addressList(String token);

    Map addressDetail(Integer addressId);

    void setSelectAddress(String token, Integer addressId);
}

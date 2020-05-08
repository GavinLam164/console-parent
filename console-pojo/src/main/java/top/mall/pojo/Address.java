package top.mall.pojo;

import java.io.Serializable;

public class Address implements Serializable {
    private Integer addressId;

    private Integer uid;

    private String areaId;

    private String addressDetail;

    private String receiver;

    private String receiverPhone;

    private Integer select;

    public Address(Integer addressId, Integer uid, String areaId, String addressDetail, String receiver, String receiverPhone, Integer select) {
        this.addressId = addressId;
        this.uid = uid;
        this.areaId = areaId;
        this.addressDetail = addressDetail;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.select = select;
    }

    public Address() {
        super();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }
}
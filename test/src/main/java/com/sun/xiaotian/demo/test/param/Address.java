package com.sun.xiaotian.demo.test.param;

public class Address {

    private final String country;

    private final String province;

    private final String city;

    private final String detailAddress;

    public Address(String country, String province, String city, String detailAddress) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.detailAddress = detailAddress;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDetailAddress() {
        return detailAddress;
    }
}

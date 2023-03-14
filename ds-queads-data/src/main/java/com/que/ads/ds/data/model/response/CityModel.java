package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.City;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityModel {
    private Integer id;
    private String name;
    private String code;
    private String postalCode;
    private int provinceId;
    private int policyCount;

    public static CityModel toModel(City city) {
        return CityModel.builder()
                .id(city.getId())
                .name(city.getName())
                .code(city.getCode())
                .postalCode(city.getPostalCode())
                .provinceId(city.getProvinceId())
                .policyCount(0)
                .build();
    }
}

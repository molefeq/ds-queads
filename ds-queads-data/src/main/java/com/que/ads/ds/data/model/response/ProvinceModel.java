package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.Province;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvinceModel {
    private int id;
    private String name;
    private String code;
    private List<CityModel> cities;
    private int policyCount;

    public static ProvinceModel toModel(Province province) {
        return ProvinceModel.builder()
                .id(province.getId())
                .name(province.getName())
                .code(province.getCode())
                .policyCount(0)
                .build();
    }
}
package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.Province;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Province", description = "Response model for province.")
public class ProvinceModel {
    @Schema(name = "id", description = "Province identifier.")
    private int id;

    @Schema(name = "name", description = "Province name.")
    private String name;

    @Schema(name = "code", description = "Province code.")
    private String code;

    @Schema(name = "cities", description = "List of cities which belong to the province.")
    private List<CityModel> cities;

    @Schema(name = "policyCount", description = "Count of polices which belong to the province.")
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
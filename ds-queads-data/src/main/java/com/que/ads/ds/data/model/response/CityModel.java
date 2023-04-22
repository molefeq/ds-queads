package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.City;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "City", description = "Response model for city.")
public class CityModel {
    @Schema(name = "id", description = "City identifier.")
    private Integer id;

    @Schema(name = "name", description = "City name.")
    private String name;

    @Schema(name = "code", description = "City code.")
    private String code;

    @Schema(name = "postalCode", description = "City postal code.")
    private String postalCode;

    @Schema(name = "provinceId", description = "Province which the city belongs.")
    private int provinceId;

    @Schema(name = "policyCount", description = "Count of polices which belong to the city.")
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

package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.SubCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryModel {
    private int id;
    private String name;
    private int categoryId;
    private int policyCount;

    public static SubCategoryModel toModel(SubCategory subCategory) {
        return SubCategoryModel.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .categoryId(subCategory.getCategoryId())
                .policyCount(0)
                .build();
    }
}

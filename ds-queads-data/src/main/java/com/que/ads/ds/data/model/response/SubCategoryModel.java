package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "SubCategory", description = "Response model for sub-category.")
public class SubCategoryModel {
    @Schema(name = "id", description = "Sub-Category identifier.")
    private int id;
    @Schema(name = "name", description = "Sub-Category name.")
    private String name;
    @Schema(name = "categoryId", description = "Category which the sub-category belongs.")
    private int categoryId;
    @Schema(name = "policyCount", description = "Count of polices which belong to the sub-category.")
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

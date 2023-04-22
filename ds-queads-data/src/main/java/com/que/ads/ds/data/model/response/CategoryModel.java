package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Category", description = "Response model for category.")
public class CategoryModel {
    @Schema(name = "id", description = "Category identifier.")
    private int id;

    @Schema(name = "name", description = "Category name.")
    private String name;

    @Schema(name = "subCategories", description = "List of sub categories which belong to the category.")
    private List<SubCategoryModel> subCategories;

    @Schema(name = "policyCount", description = "Count of polices which belong to the category.")
    private int policyCount;

    public static CategoryModel toModel(Category category) {
        return CategoryModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

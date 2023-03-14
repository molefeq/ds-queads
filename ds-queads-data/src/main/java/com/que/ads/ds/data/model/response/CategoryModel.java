package com.que.ads.ds.data.model.response;

import com.que.ads.ds.data.entity.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel {
    private int id;
    private String name;
    private List<SubCategoryModel> subCategories;
    private int policyCount;

    public static CategoryModel toModel(Category category) {
        return CategoryModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

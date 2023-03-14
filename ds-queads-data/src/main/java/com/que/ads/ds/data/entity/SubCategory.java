package com.que.ads.ds.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sub_category", schema = "que_ads")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private int categoryId;

    public static SubCategory mapToSubCategory(int subCategoryId){
        SubCategory subCategory = new SubCategory();

        subCategory.setId(subCategoryId);

        return subCategory;
    }
}

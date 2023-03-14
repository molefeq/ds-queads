package com.que.ads.ds.data.model.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyModel {
    private Integer id;
    private String subject;
    private String content;
    private String phoneNumber;
    private String images;
    private int viewCount;
    private int replyCount;
    private CategoryModel category;
    private SubCategoryModel subCategory;
    private CityModel city;
    private ProvinceModel province;
    private Timestamp editDate;
    private Timestamp createDate;
}

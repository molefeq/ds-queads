package com.que.ads.ds.data.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Policy", description = "Response model for an ad.")
public class PolicyModel {
    @Schema(name = "id", description = "Ad identifier.")
    private Integer id;

    @Schema(name = "subject", description = "Ad heading.")
    private String subject;

    @Schema(name = "content", description = "Ad description.")
    private String content;

    @Schema(name = "phoneNumber", description = "Ad contact number it is optional.")
    private String phoneNumber;

    @Schema(name = "images", description = "List of images for the ad.")
    private String images;

    @Schema(name = "viewCount", description = "Number of ad views.")
    private int viewCount;

    @Schema(name = "replyCount", description = "Number of ad replies.")
    private int replyCount;

    @Schema(name = "category", description = "Ad category.")
    private CategoryModel category;

    @Schema(name = "subCategory", description = "Ad sub category.")
    private SubCategoryModel subCategory;

    @Schema(name = "city", description = "Ad city.")
    private CityModel city;

    @Schema(name = "province", description = "Ad province.")
    private ProvinceModel province;

    @Schema(name = "editDate", description = "Latest timestamp the ad was edited.")
    private Timestamp editDate;

    @Schema(name = "createDate", description = "Timestamp the ad was created.")
    private Timestamp createDate;
}

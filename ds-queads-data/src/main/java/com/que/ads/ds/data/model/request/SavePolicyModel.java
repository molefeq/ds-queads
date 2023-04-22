package com.que.ads.ds.data.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SavePolicy", description = "Request model for adding or updating an ad.")
public class SavePolicyModel {
    @Schema(name = "id", description = "Ad identifier.")
    private Integer id;

    @NotBlank(message = "Subject is required.")
    @Size(min = 4, max = 100, message = "Subject must be at least 4 characters.")
    @Schema(name = "subject", description = "Ad heading.")
    private String subject;

    @NotBlank(message = "Content is required.")
    @Size(min = 4, message = "Content must be at least 4 characters.")
    @Schema(name = "content", description = "Ad description.")
    private String content;

    @Schema(name = "phoneNumber", description = "Ad contact number it is optional.")
    private String phoneNumber;

    @Schema(name = "images", description = "List of images for the ad.")
    private String images;

    @NotBlank(message = "Please select the sub category.")
    @Schema(name = "subCategory", description = "Ad sub category.")
    private int subCategoryId;

    @NotBlank(message = "Please select the city.")
    @Schema(name = "city", description = "Ad city.")
    private int cityId;

    @Schema(name = "user", description = "Ad creator, no need to set it from the client as it will picked from the logged in token.")
    private Integer userId;
}

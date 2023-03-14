package com.que.ads.ds.data.model.request;

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
public class SavePolicyModel {
    private Integer id;

    @NotBlank(message = "Subject is required.")
    @Size(min = 4, max = 100, message = "Subject must be at least 4 characters.")
    private String subject;

    @NotBlank(message = "Content is required.")
    @Size(min = 4, message = "Content must be at least 4 characters.")
    private String content;

    private String phoneNumber;

    private String images;

    @NotBlank(message = "Please select the sub category.")
    private int subCategoryId;

    @NotBlank(message = "Please select the city.")
    private int cityId;

    private Integer userId;
}

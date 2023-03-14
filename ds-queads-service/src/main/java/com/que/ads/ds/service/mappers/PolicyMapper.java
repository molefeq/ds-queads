package com.que.ads.ds.service.mappers;

import com.que.ads.ds.common.utils.SqlTimestampUtil;
import com.que.ads.ds.data.entity.City;
import com.que.ads.ds.data.entity.Policy;
import com.que.ads.ds.data.entity.SubCategory;
import com.que.ads.ds.data.model.request.SavePolicyModel;
import com.que.ads.ds.data.model.response.*;
import com.que.ads.ds.service.service.CategoryService;
import com.que.ads.ds.service.service.CityService;
import com.que.ads.ds.service.service.ProvinceService;
import com.que.ads.ds.service.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PolicyMapper {

    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;
    private final ProvinceService provinceService;
    private final CityService cityService;

    public PolicyModel toModel(Policy policy) {
        SubCategoryModel subCategoryModel = subCategoryService.getSubCategory(policy.getSubCategory().getId());
        CategoryModel categoryModel = categoryService.getCategory(subCategoryModel.getCategoryId(), false);
        CityModel cityModel = cityService.getCity(policy.getCity().getId());
        ProvinceModel provinceModel = provinceService.getProvince(cityModel.getProvinceId(), false);

        return PolicyModel.builder()
                .id(policy.getId())
                .subject(policy.getSubject())
                .content(policy.getContent())
                .phoneNumber(policy.getPhoneNumber())
                .viewCount(policy.getViewCount())
                .replyCount(policy.getReplyCount())
                .category(categoryModel)
                .subCategory(subCategoryModel)
                .province(provinceModel)
                .city(cityModel)
                .editDate(policy.getEditDate())
                .createDate(policy.getCreateDate())
                .build();
    }

    public Policy fromModel(SavePolicyModel savePolicyModel) {
        Policy policy = new Policy();

        policy.setSubject(savePolicyModel.getSubject());
        policy.setContent(savePolicyModel.getContent());
        policy.setImages(savePolicyModel.getImages());
        policy.setCity(City.mapToCity(savePolicyModel.getCityId()));
        policy.setSubCategory(SubCategory.mapToSubCategory(savePolicyModel.getSubCategoryId()));
        policy.setEditDate(SqlTimestampUtil.now());
        policy.setCreateDate(SqlTimestampUtil.now());
        policy.setUserId(savePolicyModel.getUserId());

        return policy;
    }

    public boolean fromModel(Policy policy, SavePolicyModel savePolicyModel) {
        boolean hasPolicyChanged = false;

        if (policy.getCity().getId() != savePolicyModel.getCityId()) {
            policy.setCity(City.mapToCity(savePolicyModel.getCityId()));
            hasPolicyChanged = true;
        }

        if (!policy.getSubject().equalsIgnoreCase(savePolicyModel.getSubject())) {
            policy.setSubject(savePolicyModel.getSubject());
            hasPolicyChanged = true;
        }

        if (!policy.getContent().equalsIgnoreCase(savePolicyModel.getContent())) {
            policy.setContent(savePolicyModel.getContent());
            hasPolicyChanged = true;
        }

        if (policy.getSubCategory().getId() != savePolicyModel.getSubCategoryId()) {
            policy.setSubCategory(SubCategory.mapToSubCategory(savePolicyModel.getSubCategoryId()));
            hasPolicyChanged = true;
        }

        if (!policy.getImages().equalsIgnoreCase(savePolicyModel.getImages())) {
            policy.setImages(savePolicyModel.getImages());
            hasPolicyChanged = true;
        }

        if (hasPolicyChanged) {
            policy.setEditDate(SqlTimestampUtil.now());
        }

        return hasPolicyChanged;
    }
}

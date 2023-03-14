package com.que.ads.ds.service.service;

import com.que.ads.ds.data.model.response.SubCategoryModel;
import com.que.ads.ds.service.caching.ReferenceDataCache;
import com.que.ads.ds.service.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubCategoryService {
    private final ReferenceDataCache referenceDataCache;
    private final PolicyRepository policyRepository;

    public SubCategoryModel getSubCategory(int subCategoryId) {
        return referenceDataCache.getSubCategories().stream()
                .filter(item -> item.getId() == subCategoryId)
                .findFirst()
                .orElse(null);
    }

    public List<SubCategoryModel> getSubCategories(int categoryId) {
        return referenceDataCache.getSubCategories().stream()
                .filter(item -> item.getCategoryId() == categoryId)
                .toList();
    }

    public List<SubCategoryModel> getSubCategoriesForCity(int categoryId, int cityId) {
        List<SubCategoryModel> subCategories = getSubCategories(categoryId);

        subCategories.stream().forEach(item -> {
            item.setPolicyCount(policyRepository.countPoliciesBySubCategoryAndCity(item.getId(), cityId));
        });

        return subCategories;
    }

    public List<SubCategoryModel> getSubCategoriesForProvince(int categoryId, int provinceId) {
        List<SubCategoryModel> subCategories = getSubCategories(categoryId);

        subCategories.stream().forEach(item -> {
            item.setPolicyCount(policyRepository.countPoliciesBySubCategoryAndProvince(item.getId(), provinceId));
        });

        return subCategories;
    }
}

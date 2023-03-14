package com.que.ads.ds.service.service;

import com.que.ads.ds.data.model.response.CategoryModel;
import com.que.ads.ds.service.caching.ReferenceDataCache;
import com.que.ads.ds.service.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final ReferenceDataCache referenceDataCache;
    private final PolicyRepository policyRepository;

    public CategoryModel getCategory(int categoryId, boolean includeCategories) {
        CategoryModel categoryModel = referenceDataCache.getCategories().stream()
                .filter(item -> item.getId() == categoryId)
                .findFirst()
                .orElse(null);

        if (!includeCategories) {
            categoryModel.setSubCategories(null);
        }

        return categoryModel;
    }

    public List<CategoryModel> getCategories(boolean includePolicyCount) {

        List<CategoryModel> categories = referenceDataCache.getCategories();

        if (includePolicyCount) {
            categories.stream().forEach(item -> {
                item.setPolicyCount(policyRepository.countPoliciesByCategory(item.getId()));
            });
        }

        return categories;
    }

    public List<CategoryModel> getCategoriesForCity(int cityId) {
        List<CategoryModel> categories = referenceDataCache.getCategories();

        categories.stream().forEach(item -> {
            item.setPolicyCount(policyRepository.countPoliciesByCategoryAndCity(item.getId(), cityId));
        });

        return categories;
    }

    public List<CategoryModel> getCategoriesForProvince(int provinceId) {
        List<CategoryModel> categories = referenceDataCache.getCategories();

        categories.stream().forEach(item -> {
            item.setPolicyCount(policyRepository.countPoliciesByCategoryAndProvince(item.getId(), provinceId));
        });

        return categories;
    }
}

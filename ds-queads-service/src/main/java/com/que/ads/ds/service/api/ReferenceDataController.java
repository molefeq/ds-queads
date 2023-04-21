package com.que.ads.ds.service.api;

import com.que.ads.ds.data.model.response.CategoryModel;
import com.que.ads.ds.data.model.response.ProvinceModel;
import com.que.ads.ds.data.model.response.SubCategoryModel;
import com.que.ads.ds.service.service.CategoryService;
import com.que.ads.ds.service.service.ProvinceService;
import com.que.ads.ds.service.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReferenceDataController {
    private final ProvinceService provinceService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping("/provinces")
    @Operation(description = "get all provinces.")
    public List<ProvinceModel> provinces() {
        return provinceService.getProvinces();
    }

    @GetMapping("/categories")
    @Operation(description = "get all categories.")
    public List<CategoryModel> categories() {
        return categoryService.getCategories(true);
    }

    @GetMapping("/categories/city/{cityId}")
    @Operation(description = "get all categories with the policy count filtered by the provided city.")
    public List<CategoryModel> categoriesByCity(@PathVariable int cityId) {
        return categoryService.getCategoriesForCity(cityId);
    }

    @GetMapping("/categories/province/{provinceId}")
    @Operation(description = "get all provinces with the policy count filtered by the provided city.")
    public List<CategoryModel> categoriesByProvince(@PathVariable int provinceId) {
        return categoryService.getCategoriesForProvince(provinceId);
    }

    @GetMapping("/sub-categories/{categoryId}")
    @Operation(description = "get all sub categories the belong to the selected category.")
    public List<SubCategoryModel> subCategories(@PathVariable int categoryId) {
        return subCategoryService.getSubCategories(categoryId);
    }

    @GetMapping("/sub-categories/{categoryId}/city/{cityId}")
    @Operation(description = "get all sub categories the belong to the selected category with the policy count filtered by the provided city.")
    public List<SubCategoryModel> subCategoriesByCity(@PathVariable int categoryId, @PathVariable int cityId) {
        return subCategoryService.getSubCategoriesForCity(categoryId, cityId);
    }

    @GetMapping("/sub-categories/{categoryId}/province/{provinceId}")
    @Operation(description = "get all sub categories the belong to the selected category with the policy count filtered by the provided province.")
    public List<SubCategoryModel> subCategoriesByProvince(@PathVariable int categoryId, @PathVariable int provinceId) {
        return subCategoryService.getSubCategoriesForProvince(categoryId, provinceId);
    }

//    @GetMapping("/v1/csrf")
//    public CsrfToken csrf(CsrfToken token) {
//        return token;
//    }
}

package com.que.ads.ds.service.api;

import com.que.ads.ds.common.data.ErrorResponse;
import com.que.ads.ds.data.model.response.CategoryModel;
import com.que.ads.ds.data.model.response.ProvinceModel;
import com.que.ads.ds.data.model.response.SubCategoryModel;
import com.que.ads.ds.service.service.CategoryService;
import com.que.ads.ds.service.service.ProvinceService;
import com.que.ads.ds.service.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Reference Data", description = "Reference data endpoints.")
public class ReferenceDataController {
    private final ProvinceService provinceService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping("/provinces")
    @Operation(description = "get all provinces.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProvinceModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<ProvinceModel> provinces() {
        return provinceService.getProvinces();
    }

    @GetMapping("/categories")
    @Operation(description = "get all categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<CategoryModel> categories() {
        return categoryService.getCategories(true);
    }

    @GetMapping("/categories/city/{cityId}")
    @Operation(description = "get all categories with the policy count filtered by the provided city.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "cityId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "city you are searching against.")
    public List<CategoryModel> categoriesByCity(@PathVariable int cityId) {
        return categoryService.getCategoriesForCity(cityId);
    }

    @GetMapping("/categories/province/{provinceId}")
    @Operation(description = "get all provinces with the policy count filtered by the provided city.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "provinceId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "province you are searching against.")
    public List<CategoryModel> categoriesByProvince(@PathVariable int provinceId) {
        return categoryService.getCategoriesForProvince(provinceId);
    }

    @GetMapping("/sub-categories/{categoryId}")
    @Operation(description = "get all sub categories the belong to the selected category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SubCategoryModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "categoryId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "category for the sub-categories.")
    public List<SubCategoryModel> subCategories(@PathVariable int categoryId) {
        return subCategoryService.getSubCategories(categoryId);
    }

    @GetMapping("/sub-categories/{categoryId}/city/{cityId}")
    @Operation(description = "get all sub categories the belong to the selected category with the policy count filtered by the provided city.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SubCategoryModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "categoryId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "category for the sub-categories.")
    @Parameter(name = "cityId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "city you are searching against.")
    public List<SubCategoryModel> subCategoriesByCity(@PathVariable int categoryId, @PathVariable int cityId) {
        return subCategoryService.getSubCategoriesForCity(categoryId, cityId);
    }

    @GetMapping("/sub-categories/{categoryId}/province/{provinceId}")
    @Operation(description = "get all sub categories the belong to the selected category with the policy count filtered by the provided province.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SubCategoryModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "categoryId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "category for the sub-categories.")
    @Parameter(name = "provinceId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "province you are searching against.")
    public List<SubCategoryModel> subCategoriesByProvince(@PathVariable int categoryId, @PathVariable int provinceId) {
        return subCategoryService.getSubCategoriesForProvince(categoryId, provinceId);
    }

//    @GetMapping("/v1/csrf")
//    public CsrfToken csrf(CsrfToken token) {
//        return token;
//    }
}

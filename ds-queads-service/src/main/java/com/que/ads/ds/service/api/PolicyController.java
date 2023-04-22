package com.que.ads.ds.service.api;

import com.que.ads.ds.common.data.ErrorResponse;
import com.que.ads.ds.data.model.request.SavePolicyModel;
import com.que.ads.ds.data.model.response.PolicyModel;
import com.que.ads.ds.security.models.response.AuthenticationResponseModel;
import com.que.ads.ds.service.service.PolicyService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Policy", description = "Ad management endpoints")
public class PolicyController {
    private final PolicyService policyService;

    @GetMapping("/policies")
    @Operation(description = "Gets all ads.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<PolicyModel> policies() {
        return policyService.getLatestPolicies();
    }

    @GetMapping("/policies/city/{cityId}")
    @Operation(description = "Gets ads by the city.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "cityId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "city you are searching against.")
    public List<PolicyModel> cityPolicies(@PathVariable int cityId) {
        return policyService.getPolicyByCity(cityId);
    }

    @GetMapping("/policies/province/{provinceId}")
    @Operation(description = "Gets ads by the province.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "provinceId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "province you are searching against.")
    public List<PolicyModel> provincePolicies(@PathVariable int provinceId) {
        return policyService.getPolicyByProvince(provinceId);
    }

    @GetMapping("/policies/city/{cityId}/category/{categoryId}")
    @Operation(description = "Gets ads by the city and category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "cityId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "city you are searching against.")
    @Parameter(name = "categoryId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "category you are searching against.")
    public List<PolicyModel> cityAndCategoryPolicies(@PathVariable int cityId, @PathVariable int categoryId) {
        return policyService.getPolicyByCityAndCategory(cityId, categoryId);
    }

    @GetMapping("/policies/province/{provinceId}/category/{categoryId}")
    @Operation(description = "Gets ads by the province and category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "provinceId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "province you are searching against.")
    @Parameter(name = "categoryId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "category you are searching against.")
    public List<PolicyModel> provinceAndCategoryPolicies(@PathVariable int provinceId, @PathVariable int categoryId) {
        return policyService.getPolicyByProvinceAndCategory(provinceId, categoryId);
    }

    @GetMapping("/policies/city/{cityId}/subCategory/{subCategoryId}")
    @Operation(description = "Gets ads by the city and sub-category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "cityId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "city you are searching against.")
    @Parameter(name = "subCategoryId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "sub-category you are searching against.")
    public List<PolicyModel> cityAndSubCategoryPolicies(@PathVariable int cityId, @PathVariable int subCategoryId) {
        return policyService.getPolicyBySubCategoryAndCity(subCategoryId, cityId);
    }

    @GetMapping("/policies/province/{provinceId}/subCategory/{subCategoryId}")
    @Operation(description = "Gets ads by the province and sub-category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "provinceId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "province you are searching against.")
    @Parameter(name = "subCategoryId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "sub-category you are searching against.")
    public List<PolicyModel> provinceAndSubCategoryPolicies(@PathVariable int provinceId, @PathVariable int subCategoryId) {
        return policyService.getPolicyBySubCategoryAndProvince(subCategoryId, provinceId);
    }

    @GetMapping("/policies-context")
    @Operation(description = "Gets ads for the user logged in.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<PolicyModel> getUserPolicies() {
        return policyService.getLoggedInUserPolicies();
    }

    @PostMapping("/save-policies")
    @Operation(description = "Create or update an ad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PolicyModel.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public PolicyModel savePolicy(@RequestBody SavePolicyModel savePolicyModel) {
        int policy = policyService.save(savePolicyModel);
        return policyService.getPolicyById(policy);
    }

    @PostMapping("/delete-policy/{policyId}")
    @Operation(description = "Delete an ad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "policyId", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "Ad identifier you are deleting.")
    public void savePolicy(@PathVariable int policyId) {
        policyService.delete(policyId);
    }
}

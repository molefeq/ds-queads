package com.que.ads.ds.service.api;

import com.que.ads.ds.data.model.request.SavePolicyModel;
import com.que.ads.ds.data.model.response.PolicyModel;
import com.que.ads.ds.service.service.PolicyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PolicyController {
    private final PolicyService policyService;

    @GetMapping("/policies")
    public List<PolicyModel> policies() {
        return policyService.getLatestPolicies();
    }

    @GetMapping("/policies/city/{cityId}")
    public List<PolicyModel> cityPolicies(@PathVariable int cityId) {
        return policyService.getPolicyByCity(cityId);
    }

    @GetMapping("/policies/province/{provinceId}")
    public List<PolicyModel> provincePolicies(@PathVariable int provinceId) {
        return policyService.getPolicyByProvince(provinceId);
    }

    @GetMapping("/policies/city/{cityId}/category/{categoryId}")
    public List<PolicyModel> cityAndCategoryPolicies(@PathVariable int cityId, @PathVariable int categoryId) {
        return policyService.getPolicyByCityAndCategory(cityId, categoryId);
    }

    @GetMapping("/policies/province/{provinceId}/category/{categoryId}")
    public List<PolicyModel> provinceAndCategoryPolicies(@PathVariable int provinceId, @PathVariable int categoryId) {
        return policyService.getPolicyByProvinceAndCategory(provinceId, categoryId);
    }

    @GetMapping("/policies/city/{cityId}/subCategory/{subCategoryId}")
    public List<PolicyModel> cityAndSubCategoryPolicies(@PathVariable int cityId, @PathVariable int subCategoryId) {
        return policyService.getPolicyBySubCategoryAndCity(subCategoryId, cityId);
    }

    @GetMapping("/policies/province/{provinceId}/subCategory/{subCategoryId}")
    public List<PolicyModel> provinceAndSubCategoryPolicies(@PathVariable int provinceId, @PathVariable int subCategoryId) {
        return policyService.getPolicyBySubCategoryAndProvince(subCategoryId, provinceId);
    }

    @GetMapping("/policies-context")
    public List<PolicyModel> getUserPolicies() {
        return policyService.getLoggedInUserPolicies();
    }

    @PostMapping("/save-policies")
    public PolicyModel savePolicy(@RequestBody SavePolicyModel savePolicyModel) {
        int policy = policyService.save(savePolicyModel);
        return policyService.getPolicyById(policy);
    }

    @PostMapping("/delete-policy/{policyId}")
    public void savePolicy(@PathVariable int policyId) {
        policyService.delete(policyId);
    }
}

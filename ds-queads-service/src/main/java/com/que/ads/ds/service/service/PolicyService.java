package com.que.ads.ds.service.service;

import com.que.ads.ds.data.entity.Policy;
import com.que.ads.ds.data.model.request.SavePolicyModel;
import com.que.ads.ds.data.model.response.PolicyModel;
import com.que.ads.ds.service.mappers.PolicyMapper;
import com.que.ads.ds.service.repository.PolicyRepository;
import com.que.ads.ds.service.repository.impl.PolicyPagingRepositoryImpl;
import com.que.ads.ds.service.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final PolicyPagingRepositoryImpl policyPagingRepository;
    private final PolicyMapper policyMapper;

    public List<PolicyModel> getLoggedInUserPolicies() {
        return getUserPolicies(UserUtils.getUserId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PolicyModel getPolicyById(int policyId) {
        var policy = policyRepository.findById(policyId);

        return policyMapper.toModel(policy.get());
    }

    public List<PolicyModel> getUserPolicies(int userId) {
        List<Policy> policies = policyRepository.findPoliciesByUser(userId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getLatestPolicies() {
        List<Policy> policies = policyPagingRepository.findLatestPolicies();

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyByCategory(int categoryId) {
        List<Policy> policies = policyRepository.findPoliciesByCategory(categoryId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyBySubCategory(int subCategoryId) {
        List<Policy> policies = policyRepository.findPoliciesBySubCategory(subCategoryId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyByProvince(int provinceId) {
        List<Policy> policies = policyRepository.findPoliciesByProvince(provinceId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyByCity(int cityId) {
        List<Policy> policies = policyRepository.findPoliciesByCity(cityId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyByCityAndCategory(int cityId, int categoryId) {
        List<Policy> policies = policyRepository.findPoliciesByCategoryAndCity(categoryId, cityId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyByProvinceAndCategory(int provinceId, int categoryId) {
        List<Policy> policies = policyRepository.findPoliciesByCategoryAndProvince(categoryId, provinceId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyBySubCategoryAndCity(int subCategoryId, int cityId) {
        List<Policy> policies = policyRepository.findPoliciesBySubCategoryAndCity(subCategoryId, cityId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    public List<PolicyModel> getPolicyBySubCategoryAndProvince(int subCategoryId, int provinceId) {
        List<Policy> policies = policyRepository.findPoliciesBySubCategoryAndProvince(subCategoryId, provinceId);

        return policies.stream().map(p -> policyMapper.toModel(p)).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int save(SavePolicyModel savePolicyModel) {
        if (savePolicyModel.getId() != null) {
            return updatePolicy(savePolicyModel);
        }

        return createPolicy(savePolicyModel);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(int policyId) {
        Policy policy = policyRepository.findById(policyId).get();
        policyRepository.delete(policy);
    }

    private int updatePolicy(SavePolicyModel savePolicyModel) {
        Policy policy = policyRepository.findById(savePolicyModel.getId()).get();
        boolean hasModelChange = policyMapper.fromModel(policy, savePolicyModel);

        if (hasModelChange) {
            policyRepository.save(policy);
        }

        return policy.getId();
    }

    private int createPolicy(SavePolicyModel savePolicyModel) {
        savePolicyModel.setUserId(UserUtils.getUserId());

        Policy policy = policyMapper.fromModel(savePolicyModel);
        policyRepository.save(policy);

        return policy.getId();
    }
}

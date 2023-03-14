package com.que.ads.ds.service.service;

import com.que.ads.ds.data.model.response.ProvinceModel;
import com.que.ads.ds.service.caching.ReferenceDataCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProvinceService {
    private final ReferenceDataCache referenceDataCache;

    public ProvinceModel getProvince(int provinceId, boolean includeCities) {
        ProvinceModel provinceModel = referenceDataCache.getProvinces().stream()
                .filter(item -> item.getId() == provinceId)
                .findFirst()
                .orElse(null);

        if (!includeCities) {
            provinceModel.setCities(null);
        }

        return provinceModel;
    }

    public List<ProvinceModel> getProvinces() {
        return referenceDataCache.getProvinces();
    }
}

package com.que.ads.ds.service.service;

import com.que.ads.ds.data.model.response.CityModel;
import com.que.ads.ds.service.caching.ReferenceDataCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final ReferenceDataCache referenceDataCache;

    public CityModel getCity(int cityId) {
        return referenceDataCache.getCities().stream()
                .filter(item -> item.getId() == cityId)
                .findFirst()
                .orElse(null);
    }

    public List<CityModel> getCities(int provinceId) {
        return referenceDataCache.getCities().stream()
                .filter(item -> item.getProvinceId() == provinceId)
                .toList();
    }
}

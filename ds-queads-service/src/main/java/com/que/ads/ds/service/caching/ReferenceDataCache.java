package com.que.ads.ds.service.caching;

import com.que.ads.ds.common.utils.CacheUtil;
import com.que.ads.ds.data.entity.Category;
import com.que.ads.ds.data.entity.City;
import com.que.ads.ds.data.entity.Province;
import com.que.ads.ds.data.entity.SubCategory;
import com.que.ads.ds.data.model.response.CategoryModel;
import com.que.ads.ds.data.model.response.CityModel;
import com.que.ads.ds.data.model.response.ProvinceModel;
import com.que.ads.ds.data.model.response.SubCategoryModel;
import com.que.ads.ds.service.repository.CategoryRepository;
import com.que.ads.ds.service.repository.CityRepository;
import com.que.ads.ds.service.repository.ProvinceRepository;
import com.que.ads.ds.service.repository.SubCategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ReferenceDataCache {
    private Long provincesLastRefresh = 0L;
    private Long categoriesLastRefresh = 0L;
    private Long citiesLastRefresh = 0L;
    private Long subCategoriesLastRefresh = 0L;

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProvinceRepository provinceRepository;
    private final CityRepository cityRepository;

    private List<ProvinceModel> provinces;
    private List<CityModel> cities;
    private List<SubCategoryModel> subCategories;
    private List<CategoryModel> categories;

    @Value("${reference.data.cache-ttl}")
    private Long cacheTimeToLive;

    @PostConstruct
    public void init() {
        log.debug("Initialising Reference Data Cache Manager...");

        loadCities();
        loadProvinces();
        loadSubCategories();
        loadCategories();

        log.debug("Done Reference Data Cache Manager...");
    }

    @Scheduled(cron = "${app.cron.expression}")
    @Transactional
    public void loadCities() {
        if (!CacheUtil.hasCacheExpired(cacheTimeToLive, citiesLastRefresh)) {
            return;
        }

        List<City> entities = cityRepository.findAll();

        cities = entities.stream().map(item -> CityModel.toModel(item)).collect(Collectors.toList());

        citiesLastRefresh = System.currentTimeMillis();
    }

    @Scheduled(cron = "${app.cron.expression}")
    @Transactional
    public void loadProvinces() {
        if (!CacheUtil.hasCacheExpired(cacheTimeToLive, provincesLastRefresh)) {
            return;
        }

        List<Province> entities = provinceRepository.findAll();

        if (CollectionUtils.isEmpty(cities)) {
            loadCities();
        }

        provinces = entities.stream().map(item -> ProvinceModel.toModel(item)).collect(Collectors.toList());

        provinces.stream().forEach(item -> {
            item.setCities(cities.stream().filter(c -> c.getProvinceId() == item.getId()).toList());
        });

        provincesLastRefresh = System.currentTimeMillis();
    }

    @Scheduled(cron = "${app.cron.expression}")
    @Transactional
    public void loadCategories() {
        if (!CacheUtil.hasCacheExpired(cacheTimeToLive, categoriesLastRefresh)) {
            return;
        }

        if (CollectionUtils.isEmpty(subCategories)) {
            loadSubCategories();
        }

        List<Category> entities = categoryRepository.findAll();

        categories = entities.stream().map(item -> CategoryModel.toModel(item)).collect(Collectors.toList());
        categories.stream().forEach(item -> {
            item.setSubCategories(subCategories.stream().filter(c -> c.getCategoryId() == item.getId()).toList());
        });

        categoriesLastRefresh = System.currentTimeMillis();
    }

    @Scheduled(cron = "${app.cron.expression}")
    @Transactional
    public void loadSubCategories() {
        if (!CacheUtil.hasCacheExpired(cacheTimeToLive, subCategoriesLastRefresh)) {
            return;
        }

        List<SubCategory> entities = subCategoryRepository.findAll();

        subCategories = entities.stream().map(item -> SubCategoryModel.toModel(item)).collect(Collectors.toList());
        subCategoriesLastRefresh = System.currentTimeMillis();
    }

    public List<ProvinceModel> getProvinces() {
        return provinces;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public List<SubCategoryModel> getSubCategories() {
        return subCategories;
    }

    public List<CityModel> getCities() {
        return cities;
    }
}

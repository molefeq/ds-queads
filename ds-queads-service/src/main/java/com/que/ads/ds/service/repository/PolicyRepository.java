package com.que.ads.ds.service.repository;

import com.que.ads.ds.data.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    @Query("""
                SELECT p from Policy p WHERE p.city.id = :cityId
            """)
    List<Policy> findPoliciesByCity(int cityId);

    @Query("""
                SELECT COUNT(p) from Policy p 
                join SubCategory sc on p.subCategory.id = sc.id
                WHERE sc.categoryId = :categoryId
            """)
    int countPoliciesByCategory(int categoryId);

    @Query("""
                SELECT p from Policy p 
                join SubCategory sc on p.subCategory.id = sc.id
                WHERE sc.categoryId = :categoryId
            """)
    List<Policy> findPoliciesByCategory(int categoryId);

    @Query("""
                SELECT COUNT(p) from Policy p 
                WHERE p.subCategory.id = :subCategoryId
            """)
    int countPoliciesBySubCategory(int subCategoryId);

    @Query("""
                SELECT p from Policy p 
                WHERE p.subCategory.id = :subCategoryId
            """)
    List<Policy> findPoliciesBySubCategory(int subCategoryId);

    @Query("""
                SELECT COUNT(p) from Policy p 
                WHERE p.subCategory.id = :subCategoryId
                  AND p.city.id = :cityId
            """)
    int countPoliciesBySubCategoryAndCity(int subCategoryId, int cityId);

    @Query("""
                SELECT p from Policy p 
                WHERE p.subCategory.id = :subCategoryId
                  AND p.city.id = :cityId
            """)
    List<Policy> findPoliciesBySubCategoryAndCity(int subCategoryId, int cityId);

    @Query("""
                SELECT p from Policy p 
                join SubCategory sc on p.subCategory.id = sc.id
                WHERE sc.categoryId = :categoryId
                  AND p.city.id = :cityId
            """)
    List<Policy> findPoliciesByCategoryAndCity(int categoryId, int cityId);

    @Query("""
                SELECT COUNT(p) from Policy p 
                join SubCategory sc on p.subCategory.id = sc.id
                WHERE sc.categoryId = :categoryId
                  AND p.city.id = :cityId
            """)
    int countPoliciesByCategoryAndCity(int categoryId, int cityId);

    @Query("""
                SELECT p from Policy p WHERE p.userId = :userId
            """)
    List<Policy> findPoliciesByUser(int userId);

    @Query("""
               SELECT p from Policy p join City c on p.city.id = c.id
               WHERE c.provinceId = :provinceId
            """)
    List<Policy> findPoliciesByProvince(int provinceId);

    @Query("""
               SELECT p from Policy p 
               join City c on p.city.id = c.id
               join SubCategory sc on p.subCategory.id = sc.id
               WHERE c.provinceId = :provinceId
                 AND sc.categoryId = :categoryId
            """)
    List<Policy> findPoliciesByCategoryAndProvince(int categoryId, int provinceId);

    @Query("""
               SELECT COUNT(p) 
               FROM Policy p 
               JOIN City c on p.city.id = c.id
               JOIN SubCategory sc on p.subCategory.id = sc.id
               WHERE c.provinceId = :provinceId
                 AND sc.categoryId = :categoryId
            """)
    int countPoliciesByCategoryAndProvince(int categoryId, int provinceId);


    @Query("""
               SELECT p from Policy p 
               join City c on p.city.id = c.id
               WHERE c.provinceId = :provinceId
                 AND p.subCategory.id = :subCategoryId
            """)
    List<Policy> findPoliciesBySubCategoryAndProvince(int subCategoryId, int provinceId);

    @Query("""
               SELECT COUNT(p) 
               FROM Policy p 
               JOIN City c on p.city.id = c.id
               WHERE c.provinceId = :provinceId
                 AND p.subCategory.id = :subCategoryId
            """)
    int countPoliciesBySubCategoryAndProvince(int subCategoryId, int provinceId);
}


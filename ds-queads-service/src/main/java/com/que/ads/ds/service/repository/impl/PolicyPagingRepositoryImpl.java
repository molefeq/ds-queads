package com.que.ads.ds.service.repository.impl;

import com.que.ads.ds.data.entity.Policy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PolicyPagingRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Policy> findLatestPolicies() {
        return entityManager.createQuery("""
                SELECT p 
                FROM Policy p 
                ORDER BY p.editDate DESC
                """)
                .setMaxResults(10).getResultList();
    }

    public List<Policy> LatestPolicies() {
        return entityManager.createQuery("""
                SELECT p 
                FROM Policy p 
                ORDER BY p.editDate DESC
                """)
                .setMaxResults(10).getResultList();
    }
}

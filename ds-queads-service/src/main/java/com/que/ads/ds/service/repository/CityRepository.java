package com.que.ads.ds.service.repository;

import com.que.ads.ds.data.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}


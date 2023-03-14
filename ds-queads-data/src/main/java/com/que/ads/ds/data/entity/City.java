package com.que.ads.ds.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "city", schema = "que_ads")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", length=50, nullable=false, unique= false)
    private String name;

    @Column(name="code", length=10, nullable=false, unique= false)
    private String code;

    @Column(name="postal_code", length=10, nullable=false, unique= false)
    private String postalCode;

    @Column(name = "province_id")
    private int provinceId;

    public static City mapToCity(int cityId){
        City city = new City();

        city.setId(cityId);

        return city;
    }
}

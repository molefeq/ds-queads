package com.que.ads.ds.data.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "category", schema = "que_ads")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", length=50, nullable=false, unique= false)
    private String name;
}

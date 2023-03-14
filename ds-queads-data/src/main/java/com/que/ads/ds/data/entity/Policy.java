package com.que.ads.ds.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "policy", schema = "que_ads")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="subject", length=100, nullable=false, unique= false)
    private String subject;

    @Column(name="content", nullable=false, unique= false)
    private String content;

    @Column(name="phone_number", length=20, nullable=false, unique= false)
    private String phoneNumber;

    @Column(name="images", nullable=false, unique= false)
    private String images;

    @Column(name="view_count", nullable=false, unique= false)
    private int viewCount;

    @Column(name="reply_count", nullable=false, unique= false)
    private int replyCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @Column(name="user_id", nullable=false, unique= false)
    private int userId;

    @Column(name="edit_date", nullable=false, unique= false)
    private Timestamp editDate;

    @Column(name="create_date", nullable=false, unique= false)
    private Timestamp createDate;
}

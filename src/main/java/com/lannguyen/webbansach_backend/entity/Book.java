package com.lannguyen.webbansach_backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "sach")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sach")
    private int id;
    @Column(name = "ten_sach", length = 256)
    private String name;
    @Column(name = "ten_tac_gia", length = 512)
    private String author;
    @Column(name = "isbn", length = 256)
    private String ISBN;
    @Column(name = "mo_ta", columnDefinition = "text")
    private String description;
    @Column(name = "gia_niem_yet")
    private double listedPrice;
    @Column(name = "gia_ban")
    private double price;
    @Column(name = "so_luong")
    private int quantity;
    @Column(name = "trung_binh_xep_hang")
    private double averageRating;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "sach_theloai",
            joinColumns = @JoinColumn(name = "ma_sach"),
            inverseJoinColumns = @JoinColumn(name = "ma_the_loai")
    )
    private List<Genre> genreList;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookImage> imageList;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviewList;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<OrderDetail> orderDetailList;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WishList> wishList;

}

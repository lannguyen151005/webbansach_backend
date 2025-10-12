package com.lannguyen.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "nguoi_dung")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nguoi_dung")
    private int id;
    @Column(name = "ho_dem", length = 256)
    private String lastName;
    @Column(name = "ten", length = 256)
    private String firstName;
    @Column(name = "ten_dang-nhap")
    private String username;
    @Column(name = "mat_khau")
    private String password;
    @Column(name = "gioi_tinh")
    private boolean sex;
    @Column(name = "email")
    private String email;
    @Column(name = "so_dien_thoai")
    private String phoneNumber;
    @Column(name = "dia_chi_mua_hang")
    private String buyingAddress;
    @Column(name = "dia_chi_giao_hang")
    private String deliveryAddress;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Review> reviewList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<WishList> wishList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "nguoidung_quyen",
            joinColumns = @JoinColumn(name = "ma_nguoi_dung"),
            inverseJoinColumns = @JoinColumn(name = "ma_quyen")
    )
    private List<Role> roleList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Order> orderList;

}

package com.lannguyen.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "hinh_thuc_thanh_toan")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hinh_thuc_thanh_toan")
    private int id;
    @Column(name = "ten_hinh_thuc_thanh_toan")
    private String name;
    @Column(name = "mo_ta")
    private String description;
    @Column(name = "chi_phi_thanh_toan")
    private double chiPhiThanhToan;
    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Order> orderList;
}

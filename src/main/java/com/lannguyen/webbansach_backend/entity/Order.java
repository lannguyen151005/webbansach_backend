package com.lannguyen.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "don_hang")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_hang")
    private int id;
    @Column(name = "ngay_tao")
    private Date creationDate;
    @Column(name = "dia_chi_mua_hang")
    private String buyingAddress;
    @Column(name = "dia_chi_giao_hang")
    private String deliveryAddress;
    @Column(name = "gia_sach")
    private float bookPrice;
    @Column(name = "phi_van_chuyen")
    private float deliveryFee;
    @Column(name = "chi_phi_thanh_toan")
    private float chiPhiThanhToan;
    @Column(name = "tong_tien")
    private float totalPrice;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "ma_nguoi_dung", nullable = false)
    private User user;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "ma_hinh_thuc_thanh_toan")
    private PaymentMethod paymentMethod;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "ma_hinh_thuc_giao_hang")
    private DeliveryMethod deliveryMethod;
}

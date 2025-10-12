package com.lannguyen.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sach_yeu_thich")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sach_yeu_thich")
    private int id;
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
    @JoinColumn(name = "ma_sach", nullable = false)
    private Book book;
}

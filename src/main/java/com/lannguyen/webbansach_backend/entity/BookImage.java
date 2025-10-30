package com.lannguyen.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "hinh_anh")
public class BookImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hinh_anh")
    private int id;
    @Column(name = "ten_hinh_anh", length = 256)
    private String name;
    @Column(name = "la_icon")
    private boolean isIcon;
    @Column(name = "duong_dan")
    private String link;
    @Column(name = "du_lieu_anh", columnDefinition = "LONGTEXT")
    @Lob
    private String data;
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

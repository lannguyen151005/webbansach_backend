package com.lannguyen.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@Data
@Table(name = "the_loai")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_the_loai")
    private int id;
    @Column(name = "ten_the_loai", length = 256)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinTable(
            name = "sach_theloai",
            joinColumns = @JoinColumn(name = "ma_the_loai"),
            inverseJoinColumns = @JoinColumn(name = "ma_sach")
    )
    private List<Book> bookList;


}

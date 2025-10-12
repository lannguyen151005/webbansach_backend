package com.lannguyen.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "quyen")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_quyen")
    private int id;
    @Column(name = "ten_quyen")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "nguoidung_quyen",
            joinColumns = @JoinColumn(name = "ma_quyen"),
            inverseJoinColumns = @JoinColumn(name = "ma_nguoi_dung")
    )
    private List<User> userList;
}

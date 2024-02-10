package ru.dzmakats.entity;

import jakarta.persistence.*;

/**
 * Created by Denis Zolotarev on 10.02.2024
 */

@Entity
@Table(name = "task", schema = "todo")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;
}

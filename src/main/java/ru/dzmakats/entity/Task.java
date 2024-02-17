package ru.dzmakats.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Denis Zolotarev on 10.02.2024
 */

@Getter
@Setter
@Entity
@Table(name = "task", schema = "todo")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "int")
    private Status status;
}

package com.mace.swfs.persistance.entities;

import com.mace.swfs.enums.RoleEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", length = 25, nullable = false)
    private RoleEnum authority;
}

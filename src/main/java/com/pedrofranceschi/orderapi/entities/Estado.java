package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Table (name = "estado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include

    private Long id;

    @Column(name = "nome", nullable = false, length = 16)
    private String nome;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;


}

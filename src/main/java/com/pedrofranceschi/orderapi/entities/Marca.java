package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "marca")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Marca{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

}

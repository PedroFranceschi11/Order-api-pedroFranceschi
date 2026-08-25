package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "marca")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Marca{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return Objects.equals(id, marca.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

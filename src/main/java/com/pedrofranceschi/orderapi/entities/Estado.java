package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table (name = "estado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "nome", nullable = false, length = 16)
    private String nome;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(id, estado.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

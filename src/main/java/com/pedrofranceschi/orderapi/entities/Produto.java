package com.pedrofranceschi.orderapi.entities;

import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Table(name = "produto")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

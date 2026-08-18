package com.pedrofranceschi.orderapi.entities;

import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produto")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

}

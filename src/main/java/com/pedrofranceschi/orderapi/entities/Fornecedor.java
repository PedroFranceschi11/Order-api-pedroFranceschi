package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter @Setter
@Table(name = "fornecedor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String contato;

    private String CNPJ;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;





}

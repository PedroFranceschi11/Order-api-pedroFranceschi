package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Table(name = "cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String CNPJ;

    private String contato;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;


}

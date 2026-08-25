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

    @Column(name = "nome", length = 51 ,nullable = false)
    private String nome;

    @Column(name = "cnpj", length = 14 ,nullable = false)
    private String CNPJ;

    private String contato;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;


}

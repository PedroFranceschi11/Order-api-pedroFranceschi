package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Table(name = "fornecedor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String nome;

    private String contato;

    private String CNPJ;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public Fornecedor() {
    }

    public Fornecedor(Integer id, String nome, String contato, String CNPJ, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.CNPJ = CNPJ;
        this.cidade = cidade;
    }


}

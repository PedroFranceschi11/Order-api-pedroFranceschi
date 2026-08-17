package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "Clientes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Id Integer;

    private String nome;

    private String CNPJ;

    private String contato;

    public Cliente() {
    }

    public Cliente(Id integer, String nome, String CNPJ, String contato) {
        Integer = integer;
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.contato = contato;
    }
}

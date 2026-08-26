package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter @Setter
@Table(name = "fornecedor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "nome", length = 51 ,nullable = false)
    private String nome;

    @Column(name = "contato", length = 11 ,nullable = false)
    private String contato;

    @Column(name = "cnpj", length = 14 ,nullable = false)
    private String CNPJ;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

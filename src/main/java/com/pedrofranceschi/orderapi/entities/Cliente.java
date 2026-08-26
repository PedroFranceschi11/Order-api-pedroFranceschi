package com.pedrofranceschi.orderapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "nome", length = 51 ,nullable = false)
    private String nome;

    @Column(name = "cnpj", length = 14 ,nullable = false)
    private String CNPJ;

    @Column(name = "contato", length = 11 ,nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}

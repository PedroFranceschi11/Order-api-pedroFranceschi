package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Cidade;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClienteDTO {

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

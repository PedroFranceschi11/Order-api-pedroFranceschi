-- ============================================================
-- V1: Criação das tabelas base do sistema de gestão de pedidos
-- ============================================================

-- Enum de Categoria
-- ATENÇÃO: valores abaixo são PLACEHOLDER. Substitua pelos nomes
-- reais de categoria antes de rodar em produção.
CREATE TYPE categoria AS ENUM ('Higiene', 'Eletro');

-- Estado
CREATE TABLE estado (
                        id   SERIAL PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        uf   VARCHAR(2)   NOT NULL
);

-- Cidade
CREATE TABLE cidade (
                        id        SERIAL PRIMARY KEY,
                        nome      VARCHAR(100) NOT NULL,
                        estado_id INTEGER      NOT NULL,
                        CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado (id)
);

-- Cliente
CREATE TABLE cliente (
                         id        SERIAL PRIMARY KEY,
                         nome      VARCHAR(150) NOT NULL,
                         cnpj      VARCHAR(18)  NOT NULL,
                         contato   VARCHAR(100),
                         cidade_id INTEGER      NOT NULL,
                         CONSTRAINT fk_cliente_cidade FOREIGN KEY (cidade_id) REFERENCES cidade (id)
);

-- Fornecedor
CREATE TABLE fornecedor (
                            id        SERIAL PRIMARY KEY,
                            nome      VARCHAR(150) NOT NULL,
                            contato   VARCHAR(100),
                            cnpj      VARCHAR(18)  NOT NULL,
                            cidade_id INTEGER      NOT NULL,
                            CONSTRAINT fk_fornecedor_cidade FOREIGN KEY (cidade_id) REFERENCES cidade (id)
);

-- Marca
CREATE TABLE marca (
                       id   SERIAL PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL
);

-- Produto
CREATE TABLE produto (
                         id        SERIAL PRIMARY KEY,
                         nome      VARCHAR(150)   NOT NULL,
                         descricao VARCHAR(500),
                         preco     NUMERIC(10, 2) NOT NULL,
                         marca_id  INTEGER        NOT NULL,
                         categoria categoria      NOT NULL,
                         CONSTRAINT fk_produto_marca FOREIGN KEY (marca_id) REFERENCES marca (id)
);

-- Pedido
CREATE TABLE pedido (
                        id          SERIAL PRIMARY KEY,
                        cliente_id  INTEGER NOT NULL,
                        data_pedido DATE    NOT NULL,
                        CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

-- Item_pedido
CREATE TABLE item_pedido (
                             id         SERIAL PRIMARY KEY,
                             pedido_id  INTEGER        NOT NULL,
                             produto_id INTEGER        NOT NULL,
                             quantidade INTEGER        NOT NULL,
                             preco      NUMERIC(10, 2) NOT NULL,
                             CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id),
                             CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id)
);

-- Produto_fornecedor (associativa, chave composta)
CREATE TABLE produto_fornecedor (
                                    produto_id    INTEGER NOT NULL,
                                    fornecedor_id INTEGER NOT NULL,
                                    PRIMARY KEY (produto_id, fornecedor_id),
                                    CONSTRAINT fk_pf_produto FOREIGN KEY (produto_id) REFERENCES produto (id),
                                    CONSTRAINT fk_pf_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id)
);